package com.main.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.main.service.TestService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TestService testService;
	
	@GetMapping("/test")
	public String test(HttpServletRequest request, Model model) {
		String a = request.getParameter("a");
		log.info("INDEX {}", a);
		Map<String, Object> resultMap = testService.test();
		model.addAttribute("test", resultMap);
		log.info("id : {}", resultMap.get("id"));
		log.info("val : {}", resultMap.get("val"));
		return "index_old";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
}
