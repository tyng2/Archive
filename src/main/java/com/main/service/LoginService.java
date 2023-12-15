package com.main.service;

import java.util.Map;

import com.main.vo.UsersVo;

public interface LoginService {
	
/* s:NAVER */
	public String naverAuthorizeURL();
	
	public String getNaverTokenAuth(Map<String, String> paramMap);
	
	public String getNaverProfile(Map<String, String> tokenMap);

	public String connNaverUserBySnsId(String snsId, Map<String, String> profileMap);
/* e:NAVER */
	
	public void loginProcess(UsersVo user);
	
	public void logout();
	
	public boolean isLogin();
	
}
