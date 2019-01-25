package com.qs.p2p.controller.annocation;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ModelAttribute 注解学习
 * https://blog.csdn.net/lovesomnus/article/details/78873089
 */
@Controller
@RequestMapping(value = "/testModelAttribute")
public class TestModelAttributeController {


    @ModelAttribute
    public void printInfos(@RequestParam(required = false) String abc, Model model) {
        model.addAttribute("test", abc);
        model.addAttribute("scq", "孙常青");
    }

    @ModelAttribute(value = "name")
    public String myModel(@RequestParam(required = false) String nameValue) {
        return nameValue;
    }

    @RequestMapping(value = "/")
    public String testAnnocation() {
        return "/test/model-attribute";
    }

}
