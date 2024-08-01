package com.heyeji.boot.main.controllers;

import com.heyeji.boot.global.exceptions.ExceptionProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 메인 페이지
 */
@Controller
@RequestMapping("/")
public class MainController implements ExceptionProcessor {
    @GetMapping
    public String index(){
        return "front/main/index";
    }
}
