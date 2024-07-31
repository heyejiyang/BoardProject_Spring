package com.heyeji.boot.member.controllers;

import com.heyeji.boot.member.services.MemberSaveService;
import com.heyeji.boot.member.validators.JoinValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@SessionAttributes("requestLogin")
@Slf4j
public class MemberController { //로그인쪽 처리는 양식만 만들면 됨 시큐리티가 처리해준다.

    private final JoinValidator joinValidator;
    private final MemberSaveService memberSaveService;

    @ModelAttribute
    private RequestLogin requestLogin(){ //세션 범위내에서 속성 추가
        return new RequestLogin();
    }

    @GetMapping("/join")
    //pc, 모바일 뷰 분리예정.. (front, mobile, admin)
    public String join(@ModelAttribute RequestJoin form)
    {
        return "front/member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors){ //에러 객체는 반드시 커맨드 객체 뒤에 위치해야함

        joinValidator.validate(form,errors);

        if(errors.hasErrors()){
            return "front/member/join"; //에러 발생시 넘어가지 않도록
        }

        memberSaveService.save(form); //회원가입 처리

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(@Valid @ModelAttribute RequestLogin form, Errors errors){ //세션 범위에서 로그인 상태 유지, 세션에 있으면  Model쪽에서 알아서 값 주입

        String code = form.getCode();
        if(StringUtils.hasText(code)){
            //코드가 있을때 에러 메시지 출력
            errors.reject(code,form.getDefaultMessage());

            //비번 만료인 경우 비번 재설정 페이지 이동
            if(code.equals("CredentialsExpired.Login")){
                return "redirect:/member/password/reset";
            }
        }

        return "front/member/login";
    }

    @ResponseBody
    @GetMapping("/test")
    public void test(Principal principal){
        //로그인한 회원 정보의 아이디 알 수 있다.
        log.info("로그인 아이디:{}",principal.getName());
    }

}
