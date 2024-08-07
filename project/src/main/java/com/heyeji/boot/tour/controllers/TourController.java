package com.heyeji.boot.tour.controllers;

import com.heyeji.boot.global.exceptions.ExceptionProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tour")
public class TourController implements ExceptionProcessor {

    /*여행지 소개 페이지*/
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, Model model){

        model.addAttribute("addCommonScript",List.of("map"));
        model.addAttribute("addScript", List.of("tour/view2"));

        return "front/tour/view";
    }
}
