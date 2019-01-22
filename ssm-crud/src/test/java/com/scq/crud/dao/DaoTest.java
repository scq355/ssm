package com.scq.crud.dao;

import com.scq.crud.bean.Department;
import com.scq.crud.bean.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Created by nudtn on 2017/8/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class DaoTest {

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    private SqlSession sqlSession;

    @Test
    public void testCRUD() {
        System.out.printf(departmentMapper.toString());

        Department department = new Department();
        department.setDeptName("部门A");
        departmentMapper.insert(department);

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insert(new Employee(null, uid, "M", uid + "@nwpu.edu.cn", 4));
        }
    }
}
