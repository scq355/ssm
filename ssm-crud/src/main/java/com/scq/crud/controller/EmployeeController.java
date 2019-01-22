package com.scq.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scq.crud.bean.Employee;
import com.scq.crud.common.Result;
import com.scq.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nudtn on 2017/8/7.
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /**
     * 单个/批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    public Result deleteEmp(@PathVariable("ids") String ids) {
        if (ids.contains("-")) {
            List<Integer> del_ids = new ArrayList<>();
            String[] string_ids = ids.split("-");

            for (String str_id : string_ids) {
                del_ids.add(Integer.parseInt(str_id));
            }
            employeeService.deleteBatch(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }

        return Result.success();
    }

    /**
     * 员工更新
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    public Result saveEmp(Employee employee) {
        employeeService.updateEmp(employee);
        return Result.success();
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public Result getEmp(@PathVariable(value = "id") Integer id) {
        Employee employee = employeeService.getEmp(id);
        return Result.success().add("emp", employee);
    }


    /**
     * 检查用户名是否可用
     * @param empName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkuser")
    public Result checkUser(@RequestParam(value = "empName") String empName) {

        String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!empName.matches(regex)) {
            return Result.fail().add("va_msg", "用户名是2-5位汉字或6-16位英文数字组合");
        }
        //数据库用户名校验
        boolean exist = employeeService.checkUser(empName);
        if (exist) {
            return Result.success();
        } else {
            return Result.fail().add("va_msg", "用户名不可用");
        }
    }

    /**
     * 保存用户
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Result saveEmp(@Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println("错误字段名：" + error.getField());
                System.out.println("错误信息：" + error.getDefaultMessage());
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Result.fail().add("error_field", map);
        } else {
            employeeService.saveEmp(employee);
            return Result.success();
        }
    }

    /**
     * json 格式返回信息
     * @param pn
     * @return
     */
    @RequestMapping("/nemps")
    @ResponseBody
    public Result getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<Employee> employees = employeeService.getAll();
        PageInfo page = new PageInfo(employees, 5);
        return Result.success().add("pageInfo", page);
    }

    /**
     * 直接页面c:foreache()返回
     * @param pn
     * @param model
     * @return
     */
    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        PageHelper.startPage(pn, 10);
        List<Employee> employees = employeeService.getAll();
        PageInfo page = new PageInfo(employees, 5);
        model.addAttribute("pageInfo", page);
        return "list";
    }

}
