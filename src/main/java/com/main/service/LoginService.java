package com.main.service;

import java.util.Map;

import com.main.vo.UsersVo;

import jakarta.servlet.http.HttpServletRequest;

public interface LoginService {
	
/* s:NAVER */
	public String naverAuthorizeURL();
	
	public String getNaverTokenAuth(Map<String, String> paramMap);
	
	public String getNaverProfile(Map<String, String> tokenMap);

	public String connNaverUserBySnsId(HttpServletRequest request, String snsId, Map<String, String> profileMap);
/* e:NAVER */
	
	public void loginProcess(HttpServletRequest request, UsersVo user);
	
}
