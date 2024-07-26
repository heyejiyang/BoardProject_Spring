package com.heyeji.boot.member.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController { //로그인쪽 처리는 양식만 만들면 됨 시큐리티가 처리해준다.

    @GetMapping("/join")
    //모바일, pc 뷰 분리예정.. (front, mobile, admin)
    public String join(){
        return "front/member/join";
    }

    @PostMapping("/join")
    public String joinPs(){
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(){
        return "front/member/login";
    }
}
