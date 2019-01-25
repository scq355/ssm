package com.qs.p2p.controller;

import com.google.common.base.Strings;
import com.qs.p2p.constant.Constants;
import com.qs.p2p.dao.Expressions;
import com.qs.p2p.dao.OrderBy;
import com.qs.p2p.model.User;
import com.qs.p2p.pagination.Pagination;
import com.qs.p2p.pagination.PaginationUtils;
import com.qs.p2p.service.UserService;
import com.qs.p2p.utils.ExcelUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

/**
 * @description 主要是对数据库接口功能测试
 * Created by nudtn on 2017/4/30.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
    private UserService userService;

    @RequestMapping(value = {"", "/"})
	public String index(String userName, String mobile, String registBegin, String registEnd,
						@RequestParam(defaultValue = "1") int pageNumber, Model model) {
    	model.addAttribute("userName", userName);
    	model.addAttribute("mobile", mobile);
    	model.addAttribute("registBegin", registBegin);
    	model.addAttribute("registEnd", registEnd);
    	model.addAttribute("pageNumber", pageNumber);

    	User userVO = new User();
    	if (!Strings.isNullOrEmpty(userName)) {
    		userVO.and(Expressions.like("userName", "%" + userName + "%"));
		}
    	if (!Strings.isNullOrEmpty(mobile)) {
			userVO.setPhoneNumber(mobile);
		}
		try {
			if (!Strings.isNullOrEmpty(registBegin)) {
				userVO.and(Expressions.ge("registerTime", Constants.DATE_FORMAT.parse(registBegin)));
			}
			if (!Strings.isNullOrEmpty(registEnd)) {
				registEnd += " 23:59:59";
				userVO.and(Expressions.le("registerTime", Constants.DATE_TIME_FORMAT.parse(registEnd)));
			}
		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
		}
		OrderBy orderBy = new OrderBy().add("id", false);
		int count = userService.count(userVO);
		List<User> userList = userService.find(userVO, orderBy, Constants.PAGE_SIZE, pageNumber);
		Pagination<User> p = PaginationUtils.newPagination(Constants.PAGE_SIZE, pageNumber, count, userList);
		model.addAttribute("p", p);
		model.addAttribute("count", count);
		return "/user/user-home";
	}


	/**
	 * excel导出
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void export(String userName, String mobile, String registBegin, String registEnd, Model model) {
		model.addAttribute("userName", userName);
		model.addAttribute("mobile", mobile);
		model.addAttribute("registBegin", registBegin);
		model.addAttribute("registEnd", registEnd);

		User userVO = new User();
		if (!Strings.isNullOrEmpty(userName)) {
			userVO.and(Expressions.like("userName", "%" + userName + "%"));
		}
		if (!Strings.isNullOrEmpty(mobile)) {
			userVO.setPhoneNumber(mobile);
		}
		try {
			if (!Strings.isNullOrEmpty(registBegin)) {
				userVO.and(Expressions.ge("registerTime", Constants.DATE_FORMAT.parse(registBegin)));
			}
			if (!Strings.isNullOrEmpty(registEnd)) {
				registEnd += " 23:59:59";
				userVO.and(Expressions.le("registerTime", Constants.DATE_TIME_FORMAT.parse(registEnd)));
			}
		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
		}
		OrderBy orderBy = new OrderBy().add("id", false);
		List<User> userList = userService.find(userVO, orderBy);

		List<Map<String, Object>> list = new ArrayList<>();
		for (User user: userList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", user.getId());
			map.put("userName", user.getUserName());
			map.put("registerTime", DateFormatUtils.format(user.getRegisterTime(), "yyyy-MM-dd HH:mm:ss"));
			map.put("phoneNumber", user.getPhoneNumber());
			map.put("email", user.getEmail());
			list.add(map);
		}

		Map<String, Object> params = new HashMap<>();
		params.put("list", list);

		String filename = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + ".xlsx";
		ExcelUtils.exportToExcel("user-template.xlsx", params, filename);
	}


    @RequestMapping(value = "/getOne/{id}", method = RequestMethod.GET)
	public String getOne(@PathVariable(value = "id") Integer id, Model model) {
    	User user = userService.get(id);
    	Integer userNum = userService.count();
    	model.addAttribute("userNum", userNum);
    	model.addAttribute("userEntity", user);
    	return "/userInfo";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<User> userList = userService.find();
		model.addAttribute("userList", userList);
		model.addAttribute("userNum", userList.size());
		return "/user/user";
	}

	@RequestMapping(value = "/listInOrder", method = RequestMethod.GET)
	@ResponseBody
	public String listInOrder() {
		OrderBy orderBy = new OrderBy().add("id", false);
		List<User> user = userService.find(orderBy);
		return user.toString();
	}

	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String userList(@RequestParam(defaultValue = "10") Integer pageSize,
						   @RequestParam(defaultValue = "1") Integer pageNumber,
						   Model model) {
		List<User> userList = userService.find(pageSize, pageNumber);
		model.addAttribute("userList", userList);
		return "/user/user-list";
	}


}
