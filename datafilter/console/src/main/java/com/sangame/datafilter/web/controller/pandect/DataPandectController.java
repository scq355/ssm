package com.sangame.datafilter.web.controller.pandect;

import com.sangame.datafilter.common.persistence.model.pandect.DataCount;
import com.sangame.datafilter.common.persistence.model.pandect.DataCountStatistic;
import com.sangame.datafilter.constant.PageConstant;
import com.sangame.datafilter.service.pandect.FilterDataShowService;
import com.sangame.datafilter.util.Render;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Mao Renwei
 * @Description 数据总览
 * @date 2017/5/2.
 */
@Controller
@RequestMapping(value = "/data")
public class DataPandectController {

    @Autowired
    private FilterDataShowService filterDataShowService;


    @RequestMapping("/index.do")
    public String index(){
        return PageConstant.PANDECT_INDEX_PAGE;
    }

    @ResponseBody
    @RequestMapping(value = "/listDataCount.do")
    public Render searchData(){
        List<DataCount> dataCounts = filterDataShowService.getDataCount();
        Render render = new Render(true,"查询成功",0,dataCounts);
        return render;
    }

    @ResponseBody
    @RequestMapping(value = "/listDataCountStatistic.do")
    public Render searchData(Integer projectType, Integer statusData){
        List<DataCountStatistic> dataCountStatistics = filterDataShowService.getDataCountStatistic(projectType,statusData);
        Render render = new Render(true,"查询成功",dataCountStatistics);
        return render;
    }
}
