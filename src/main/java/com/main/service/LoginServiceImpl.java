package com.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String CLIENT_ID_NAVER, SECRET_NAVER;
	
	@Value("${naver.client-id")
	private void setClientId(String clientId) {
		CLIENT_ID_NAVER = clientId;
	}
	
	@Value("${naver.secret")
	private void setSecret(String secret) {
		SECRET_NAVER = secret;
	}

	@Override
	public String naverAuthorize() {
		log.info("naver oauth2.0/authorize");
		return null;
	}

	@Override
	public String naverToken() {
		log.info("naver oauth2.0/token");
		return null;
	}
	

}
