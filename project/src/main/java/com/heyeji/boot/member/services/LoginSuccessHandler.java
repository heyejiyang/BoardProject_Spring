package com.heyeji.boot.member.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * 성공시 유입되는 클래스
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    //로그인 성공시 유입되는 메서드
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Authentication: 로그인한 사용자의 인증 정보 담겨있음

        HttpSession session = request.getSession();
        //세션에 남아있는 requestLogin 값 제거
        session.removeAttribute("requestLogin");

        //로그인 성공시 - redirectUrl이 있으면 해당 주소로 이동, 아니면 메인 페이지 이동
        String redirectUrl = request.getParameter("redirectUrl");
        redirectUrl = StringUtils.hasText(redirectUrl) ? redirectUrl.trim() : "/";

        response.sendRedirect(request.getContextPath() + redirectUrl);
    }
}
