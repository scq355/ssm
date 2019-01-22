package com.sangame.controller.file;

import com.sangame.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by nudtn on 2017/4/30.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    //TODO 文件上传
    @RequestMapping("/upload")
    public Result fileupload(@RequestParam(value = "file")CommonsMultipartFile file, HttpServletRequest request) {
        return Result.SUCCESS.copyThis();
    }
}
