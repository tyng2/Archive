package com.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.main.service.HomeService;
import com.main.vo.MenuVo;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CommonControllerAdvice {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private HomeService homeService;
	
	@ModelAttribute
	public void handleRequest(HttpServletRequest request, Model model) {
		List<MenuVo> menuList = homeService.getMenuList();
		model.addAttribute("menuList", menuList);
		log.info("menu :: {}", menuList);
	}

}
