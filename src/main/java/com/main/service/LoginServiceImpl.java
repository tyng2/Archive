package com.main.service;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.main.comm.Common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginServiceImpl implements LoginService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String CLIENT_ID_NAVER, CLIENT_SECRET_NAVER, DOMAIN;
	
	@Value("${naver.client-id}")
	private void setClientId(String clientId) {
		CLIENT_ID_NAVER = clientId;
	}
	
	@Value("${naver.client-secret}")
	private void setSecret(String secret) {
		CLIENT_SECRET_NAVER = secret;
	}
	
	@Value("${domain}")
	private void setDomain(String domain) {
		DOMAIN = domain;
	}

	@Override
	public String naverAuthorizeURL(HttpServletRequest request) {
		log.info("naver oauth2.0/authorize");
		
		String redirectURL = URLEncoder.encode(DOMAIN + "/oauth/login");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += "&client_id=" + CLIENT_ID_NAVER;
	    apiURL += "&redirect_uri=" + redirectURL;
	    apiURL += "&state=" + state;
		HttpSession session = request.getSession();
		session.setAttribute("state", state);
	    
		return apiURL;
	}
	
	@Override
	public String getNaverTokenAuth(Map<String, String> paramMap) {
		log.info("naver oauth2.0/token");
		
		String code		= Common.nvl(paramMap.get("code"));
		String state	= Common.nvl(paramMap.get("state"));
		
		String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
		apiURL += "&client_id=" + CLIENT_ID_NAVER;
		apiURL += "&client_secret=" + CLIENT_SECRET_NAVER;
		apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
		
	    String respToken = Common.callAPI(apiURL, "GET", null);
	    
		return respToken;
	}
	
	@Override
	public String getNaverProfile(Map<String, String> tokenMap) {
		log.info("naver profile");
		
		String accessToken	= Common.nvl(tokenMap.get("access_token")); 
		String tokenType	= Common.nvl(tokenMap.get("token_type")); 
		
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		String header = tokenType + " " + accessToken;
		
		String respProfile = Common.callAPI(apiURL, "GET", header);
		
		return respProfile;
	}

	

}
