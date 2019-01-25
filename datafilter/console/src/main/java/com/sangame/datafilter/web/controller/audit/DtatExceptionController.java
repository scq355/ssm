package com.sangame.datafilter.web.controller.audit;

import com.sangame.datafilter.common.persistence.model.audit.FilterDataException;
import com.sangame.datafilter.constant.PageConstant;
import com.sangame.datafilter.service.audit.FilterDataExceptionService;
import com.sangame.datafilter.util.Render;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mao Renwei
 * @Description 异常数据处理
 * @date 2017/5/9.
 */
@Controller
@RequestMapping(value = "/exception")
public class DtatExceptionController {

    @Autowired
    private FilterDataExceptionService filterDataExceptionService;

    @RequestMapping(value = "/index.do")
    public String index(){
        return PageConstant.EXCEPTION_INDEX_PAGE;
    }

    @ResponseBody
    @RequestMapping(value = "/list.do")
    public Render list(FilterDataException filterDataException){
        Render render = filterDataExceptionService.searchDatas(filterDataException);
        return render;
    }

    @ResponseBody
    @RequestMapping(value = "/updateStatus.do")
    public Render updateStatus(FilterDataException dataException){
        long startTime = System.currentTimeMillis();
        Render render = new Render(true,"异常数据处理中. . .");
        filterDataExceptionService.updateStatus(dataException);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        return render;
    }
}
