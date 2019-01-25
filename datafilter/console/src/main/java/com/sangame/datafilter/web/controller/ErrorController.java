package com.sangame.datafilter.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController extends BaseController{
	
	private final static Logger Log = LoggerFactory.getLogger(ErrorController.class);

	@RequestMapping(value="/404", method=RequestMethod.GET)
	public String error404(ModelMap model) {
		return "error 404";
	}
	
	@ResponseBody
	@RequestMapping(value="/500", method=RequestMethod.GET)
	public String error500(ModelMap model) {
		return "error 500";
	}
	
}
