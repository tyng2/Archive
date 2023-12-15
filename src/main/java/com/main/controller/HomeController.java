package com.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.main.service.LoginService;
import com.main.service.TestService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/test")
	public String test(HttpServletRequest request, Model model) {
//		log.info("ISLOGIN :: {}", LoginService.isLogin());
//		if (LoginService.isLogin()) {
//			log.info("A");
//		} else {
//			log.info("B");
//		}
		return "index_old";
	}
	
	@GetMapping("/")
	public String index() {
		boolean isLogin = loginService.isLogin();
		log.info("{}",isLogin);
		
		return "index";
	}
	
	
}
