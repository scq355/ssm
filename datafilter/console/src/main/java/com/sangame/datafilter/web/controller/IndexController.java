package com.sangame.datafilter.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sangame.datafilter.constant.PageConstant;
import com.sangame.datafilter.dto.AdminDto;
import com.sangame.datafilter.util.ConsoleUtil;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

	private final static Logger Log = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String index(ModelMap model) {
		AdminDto adminDto = ConsoleUtil.getCurrentAdmin();
		model.addAttribute("admin", adminDto);
		return PageConstant.INDEX_PAGE;
	}
	

}
