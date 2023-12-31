package com.main.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.comm.Common;
import com.main.comm.SessionUtil;
import com.main.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		
		String redirectURI = Common.nvl(request.getParameter("redirectURI"));
		if (!"".equals(redirectURI)) {
			HttpSession session = request.getSession();
			session.setAttribute("loginRedirect", redirectURI);
		}
		return "user/login";
	}
	
	@GetMapping("/naver-login")
	public void naverLogin(HttpServletRequest request, HttpServletResponse response) {
		log.info("naverLogin");
		String apiURL = loginService.naverAuthorizeURL();
		try {
			response.sendRedirect(apiURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/oauth/login")
	public String naverLoginCallback(@RequestParam Map<String, String> paramMap, HttpServletRequest request, HttpServletResponse response) {
		log.info("naverLoginCallback");
//		String error			= Common.nvl(paramMap.get("error"));
//		String err_description	= Common.nvl(paramMap.get("err_description"));
		
		String redirectURI = "";
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			String respToken	= loginService.getNaverTokenAuth(paramMap);
			Map<String, String> tokenMap	= mapper.readValue(respToken, Map.class);
			
			String respProfile	= loginService.getNaverProfile(tokenMap);
			Map<String, Object> profMap		= mapper.readValue(respProfile, Map.class);
			
			String resultCode	= Common.nvl((String) profMap.get("resultcode"));
			String message		= Common.nvl((String) profMap.get("message"));
			log.info(resultCode);
			log.info(message);
			
			Map<String, String> respMap = mapper.convertValue(profMap.get("response"), Map.class);
			
			HttpSession session = request.getSession();
			session.setAttribute("naver_profile", respMap);
			
			Object loginRedirect = session.getAttribute("loginRedirect");
			redirectURI = (loginRedirect != null) ? (String) loginRedirect : "";
			session.removeAttribute("loginRedirect");
			
			String id = Common.nvl(respMap.get("id"));
			
			String msg = loginService.connNaverUserBySnsId(request, id, respMap);
			log.info(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/"+redirectURI;
	}
	
	@GetMapping("/oauth/token")
	public String naverNaverProfile(@RequestParam Map<String, String> paramMap) {
		log.info("|| NAVER LOGIN ||");
		
//		String code				= Common.nvl(paramMap.get("code"));
//		String state			= Common.nvl(paramMap.get("state"));
//		String error			= Common.nvl(paramMap.get("error"));
//		String err_description	= Common.nvl(paramMap.get("err_description"));
		
//		loginService.getNaverProfile(code);
		
		return "";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		log.info("logout");
		SessionUtil.logout(request);
		
		return "redirect:/";
	}

}
