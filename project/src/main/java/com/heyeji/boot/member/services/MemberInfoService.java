package com.heyeji.boot.member.services;

import com.heyeji.boot.member.MemberInfo;
import com.heyeji.boot.member.constants.Authority;
import com.heyeji.boot.member.entities.Authorities;
import com.heyeji.boot.member.entities.Member;
import com.heyeji.boot.member.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 정보조회
 */
@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    private final MemberRepository memberRepository;
    //회원 정보가 필요할때마다 호출되는 메서드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //인증 인가 로그인 세션에서 username 가져와서 회원정보 넘겨주는 등 여러곳에서 가져와서 회원정보 반환하는 역할

        Member member = memberRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(username)); //회원 없을경우 usernameNotFoundException 예외 던짐

        //권한
        //memberInfo쪽에 getAuthorities() 메서드로 사용자 권한 조회
        List<Authorities> tmp = Objects.requireNonNullElse(member.getAuthorities(),List.of(Authorities.builder().member(member).authority(Authority.USER).build()));//null일때 기본값 넣음 - USER

        //가공
        List<SimpleGrantedAuthority> authorities = tmp
                .stream().map( a -> new SimpleGrantedAuthority(a.getAuthority() .name()))/*매개변수는 문자열로 들어가야하기 때문에 String인 name으로(authority는 enum상수로 되어있음)*/.toList(); // 이 정보를 가지고 시큐리티쪽에서 인가함

        return MemberInfo.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .member(member)
                .authorities(authorities)
                .build();
    }
}
