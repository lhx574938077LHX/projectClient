package com.xiaocui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping("indexV")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}

	@RequestMapping("loginV")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	@RequestMapping("blankV")
	public String blank(HttpServletRequest request, HttpServletResponse response) {
		return "blank";
	}

	@RequestMapping("loginIntercept")
	public String loginIntercept(HttpServletRequest request, HttpServletResponse response) {
		return "loginIntercept";
	}	
}
