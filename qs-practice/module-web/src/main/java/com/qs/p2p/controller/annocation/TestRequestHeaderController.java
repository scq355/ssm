package com.qs.p2p.controller.annocation;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/testRequestHeader")
public class TestRequestHeaderController {

    @GetMapping(value = "")
    public String show22(@RequestHeader("User-Agent")String browser,
                         @RequestHeader("Accept-Encoding") String encoding, Model model){
        model.addAttribute("s", browser + " " + encoding);
        System.out.println("hello" + browser);
        return "/test/request-headler";
    }
}
