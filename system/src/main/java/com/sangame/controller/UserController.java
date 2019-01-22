package com.sangame.controller;

import com.calanger.common.dao.OrderBy;
import com.sangame.model.User;
import com.sangame.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by nudtn on 2017/4/30.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
	@ResponseBody
	public String getOne(Integer id) {
    	User user = userService.get(1);
    	return user.toString();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public String list() {
		List<User> user = userService.find();
		return user.toString();
	}

	@RequestMapping(value = "/listInOrder", method = RequestMethod.GET)
	@ResponseBody
	public String listInOrder() {
		OrderBy orderBy = new OrderBy().add("id", false);
		List<User> user = userService.find(orderBy);
		return user.toString();
	}

	@RequestMapping(value = "/listInPage", method = RequestMethod.GET)
	@ResponseBody
	public String listInPage() {
		List<User> user = userService.find(5,1);
		return user.toString();
	}
}
