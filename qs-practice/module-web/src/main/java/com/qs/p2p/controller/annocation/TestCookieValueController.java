package com.qs.p2p.controller.annocation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/testCookieValue")
public class TestCookieValueController {

    @RequestMapping(value = "/1")
    public String toHello() {
        return "/test/hello";
    }

    @RequestMapping(value = "/")
    public String testCookieValue(@CookieValue(value = "name", required = false) String name,
                                  @CookieValue(value="age",required=false) Integer age) {

        System.out.printf(name +  " " + age);

        return "/test/cookie-value";

    }
}
