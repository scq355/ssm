package com.scq.crud.dao;

import com.github.pagehelper.PageInfo;
import com.scq.crud.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * Created by nudtn on 2017/8/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mvc.xml"})
public class MvcTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps")
                        .param("pn", "5")).andReturn();

        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");

        System.out.println("当前页码 " + pageInfo.getPageNum());
        System.out.println("总页码 " + pageInfo.getPages());
        System.out.println("总记录数 " + pageInfo.getTotal());
        System.out.printf("连续显示的页码 ");
        int[] nums = pageInfo.getNavigatepageNums();
        for (int i : nums) {
            System.out.println(" " + i);
        }

        List<Employee> employees = pageInfo.getList();
        for (Employee em : employees) {
            System.out.println(em.getEmpName() + "    " + em.getGender() + "   " + em.getEmail());
        }
    }
}
