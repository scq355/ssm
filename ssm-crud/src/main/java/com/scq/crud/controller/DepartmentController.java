package com.scq.crud.controller;

import com.scq.crud.bean.Department;
import com.scq.crud.common.Result;
import com.scq.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by nudtn on 2017/8/8.
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/depts")
    @ResponseBody
    public Result getDepts() {
        List<Department> departments = departmentService.getDepts();
        return Result.success().add("depts", departments);
    }
}
