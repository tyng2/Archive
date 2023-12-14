package com.main.service;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

public interface LoginService {
	
	public String naverAuthorizeURL(HttpServletRequest request);
	
	public String getNaverTokenAuth(Map<String, String> paramMap);
	
	public String getNaverProfile(Map<String, String> tokenMap);
	

}
