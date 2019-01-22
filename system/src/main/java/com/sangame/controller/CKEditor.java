package com.sangame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author scq
 * @Date 2017/5/14.
 */
@Controller
@RequestMapping(value = "/editor")
public class CKEditor {
    @ResponseBody
    @RequestMapping(value = "/index")
    public String indexEditor() {
        return "/html/ckeditor";
    }
}
