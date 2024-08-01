package com.heyeji.boot.global.advices;


import com.heyeji.boot.member.MemberUtil;
import com.heyeji.boot.member.entities.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice("com.heyeji.boot") //패키지 포함 하위 클래스 적용
@RequiredArgsConstructor
public class CommonControllerAdvice{
    private final MemberUtil memberUtil;

    @ModelAttribute("loggedMember")
    public Member loggedMember(){
        return memberUtil.getMember();
    }

    @ModelAttribute("isLogin")
    public boolean isLogin(){
        return memberUtil.isLogin();
    }

    @ModelAttribute("isAdmin")
    public boolean isAdmin(){
        return memberUtil.isAdmin();
    }

}
