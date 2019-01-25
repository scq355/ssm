package com.qs.p2p.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description 页面展示相关
 * Created by scq on 2018-01-10 15:55:49
 */
@Controller
public class IndexController extends BaseController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "/index";
	}

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	@ResponseBody
	public String maps() {
		return new String("ASDFASD");
//		return "/maps";
	}

	@RequestMapping(value = "/maps", method = RequestMethod.GET)
	public String map() {
		return "/maps";
	}

}
