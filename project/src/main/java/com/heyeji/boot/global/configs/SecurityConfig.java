package com.heyeji.boot.global.configs;

import com.heyeji.boot.member.services.LoginFailureHandler;
import com.heyeji.boot.member.services.LoginSuccessHandler;
import com.heyeji.boot.member.services.MemberAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 스프링 시큐리티 전용 설정 클래스
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    //스프링 시큐리티의 주요 설정 항목들 넣어주는 메서드
    //매개변수인 http에서 받아옴
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*로그인 로그아웃 S*/
        http.formLogin(f -> {
            f.loginPage("/member/login") //로그인 데이터를 제출 할 경로
                .usernameParameter("email") //이메일 항목으로 username을 설정한다는걸 알려주기
                    .passwordParameter("password") //개발자마다 다르게 구현 할 부분 알려줘야함
                    .successHandler(new LoginSuccessHandler())//로그인 성공시 유입
                    .failureHandler(new LoginFailureHandler()); //실패시 유입

        });//도메인 별로(영역) 나눠서 설정하도록 람다형태로 설정이 바뀌어있음

        http.logout(f ->{
           f.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) //로그아웃 처리 주소
                   .logoutSuccessUrl("/member/login");//로그아웃 성공시 이동할 주소
            //로그 기록하거나 후속 처리가 필요할 경우 핸들러 정의하면 됨
        });
        /*로그인 로그아웃 E*/

        /* 인가(접근 통제) 설정 S*/
        http.authorizeHttpRequests(a ->{ //~/** -> ~포함 하위경로 전체
           a.requestMatchers("/mypage/**").authenticated()
                   .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                   .anyRequest().permitAll();
           //해당 주소 어떻게 접근 할 수 있는지 권한에 대한 부분 설정 , authenticated(): 회원 전용, 관리자 페이지, 다른 주소는 모든 접근 권한 허용
        });

        http.exceptionHandling(a ->{
            a.authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                    .accessDeniedHandler((req,res,e)-> {
                       res.sendError(HttpStatus.UNAUTHORIZED.value());
                    });
        });
        /* 인가(접근 통제) 설정 E*/

        //iframe 자원 출처를 같은 서버 자원으로 한정
        http.headers(a -> a.frameOptions(f -> f.sameOrigin()));

        return http.build();
    }

    //스프링 시큐리티에 해시화 기능 등록되어있음
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //encode 메서드 - 해시화 해줌
    }
}
