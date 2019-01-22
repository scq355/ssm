package com.sangame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nudtn on 2017/4/30.
 */
@Controller
public class BaseController {

    @RequestMapping("/")
    public String index() {
        return "/jsp/hello";
    }

    @RequestMapping("/free")
    public String freemarker(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("free");
        modelAndView.addObject("title", "Spring MVC and Freemarker");
        modelAndView.addObject("content", "Hello world, test my first Sptingmvc Freemarker");
        return "/jsp/hello";
    }
}
