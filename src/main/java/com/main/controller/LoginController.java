package com.main.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/oauth")
	public String naverLogin(@RequestParam Map<String, String> paramMap) {
		
		return "";
	}
	
	@GetMapping("/oauth/login")
	public String naverLoginCallback(@RequestParam Map<String, String> paramMap) {
		log.info("|| NAVER LOGIN ||");
		
		return "";
	}

}
