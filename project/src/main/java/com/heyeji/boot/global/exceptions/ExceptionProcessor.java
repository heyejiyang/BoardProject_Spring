package com.heyeji.boot.global.exceptions;

import com.heyeji.boot.global.exceptions.script.AlertBackException;
import com.heyeji.boot.global.exceptions.script.AlertException;
import com.heyeji.boot.global.exceptions.script.AlertRedirectException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public interface ExceptionProcessor {
    @ExceptionHandler(Exception.class) //필요할때만 추가해서 사용
    default ModelAndView errorHandler(Exception e, HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; //기본 응답 코드 500

        String tpl = "error/error";
        if(e instanceof CommonException commonException){ //정의한 CommonException의 하위 객체이면 응답 코드 가져옴
            status = commonException.getStatus();

            if(e instanceof AlertException){
                tpl = "common/_execute_script"; //자바스크립트 Alert형태일경우
                String script = String.format("alert('%s');", e.getMessage());

                if(e instanceof AlertBackException alertBackException){
                    script += String.format("%s.history.back();",alertBackException.getTarget());
                }

                if(e instanceof AlertRedirectException alertRedirectException){

                    String url = alertRedirectException.getUrl();
                    if(!url.startsWith("http")){ //외부 URL이 아닌 경우
                        url = request.getContextPath() + url;
                    }

                    script += String.format("%s.location.replace('%s');",alertRedirectException.getTarget(), url);
                }

                mv.addObject("script", script);
            }

        }

        String url = request.getRequestURI();
        String qs = request.getQueryString(); //쿼리 스트링 있을 경우 붙여서 주소 완성

        if(StringUtils.hasText(qs)) url += "?" + qs;

        mv.addObject("message", e.getMessage());
        mv.addObject("status", status.value()); //숫자로 상태코드 담음
        mv.addObject("method",request.getMethod());// 요청 정보
        mv.addObject("path",url);//경로
        mv.setStatus(status); //응답코드 설정
        mv.setViewName(tpl);

        return mv;
    }
}
