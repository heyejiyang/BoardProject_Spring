package com.heyeji.boot.member.controllers;

import com.heyeji.boot.member.services.MemberSaveService;
import com.heyeji.boot.member.validators.JoinValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController { //로그인쪽 처리는 양식만 만들면 됨 시큐리티가 처리해준다.

    private final JoinValidator joinValidator;
    private final MemberSaveService memberSaveService;

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
    public String login(){
        return "front/member/login";
    }
}
