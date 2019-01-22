package com.scq.crud.service;

import com.scq.crud.bean.Department;
import com.scq.crud.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nudtn on 2017/8/8.
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getDepts() {
        return departmentMapper.selectByExample(null);
    }
}
