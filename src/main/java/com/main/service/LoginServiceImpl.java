package com.main.service;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.main.comm.Common;
import com.main.mapper.UsersMapper;
import com.main.vo.LoginSessionVo;
import com.main.vo.UsersVo;

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
	
	@Autowired
	private UsersMapper usersMapper;

	
/* s:NAVER */
	@SuppressWarnings("deprecation")
	@Override
	public String naverAuthorizeURL() {
		log.info("naver oauth2.0/authorize");
		
		String redirectURL = URLEncoder.encode(DOMAIN + "/oauth/login");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize";
		Map<String, String> q = Map.of(
			"response_type"	, "code",
			"client_id"		, CLIENT_ID_NAVER,
			"redirect_uri"	, redirectURL,
			"state"			, state
		);
		apiURL += Common.getQueryString(q);
	    
	    HttpServletRequest request	= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("state", state);
	    
		return apiURL;
	}
	
	@Override
	public String getNaverTokenAuth(Map<String, String> paramMap) {
		log.info("naver oauth2.0/token");
		
		String code		= Common.nvl(paramMap.get("code"));
		String state	= Common.nvl(paramMap.get("state"));
		
		String apiURL = "https://nid.naver.com/oauth2.0/token";
		Map<String, String> q = Map.of(
			"grant_type"	, "authorization_code",
			"client_id"		, CLIENT_ID_NAVER,
			"client_secret"	, CLIENT_SECRET_NAVER,
			"code"			, code,
			"state"			, state
		);
		apiURL += Common.getQueryString(q);
		
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

	@Override
	public String connNaverUserBySnsId(String snsId, Map<String, String> profileMap) {
		UsersVo user	= usersMapper.getUsersBySnsId(snsId);
		String msg		= "";
		
		// user 이미 존재
		if (!ObjectUtils.isEmpty(user)) {
			// 프로필 최신화 (UPDATE users)
			user.setNickname(profileMap.get("nickname"));
			user.setUserName(profileMap.get("name"));
			user.setEmail(profileMap.get("email"));
			user.setSnsProfile(profileMap.get("profile_image"));
			user.setMobile(profileMap.get("mobile"));
			
			int cnt = usersMapper.updateExistUsers(user);
			log.info("UPDATE :: {}", cnt);
			msg = "update";
			
		} else {
			user = new UsersVo();
			user.setNickname(profileMap.get("nickname"));
			user.setUserName(profileMap.get("name"));
			user.setEmail(profileMap.get("email"));
			user.setSnsProfile(profileMap.get("profile_image"));
			user.setMobile(profileMap.get("mobile"));
			user.setSnsId(snsId);
			user.setSnsType("naver");
			user.setAuthId(2);
			
			int userId = usersMapper.insertUsers(user);
			log.info("INSERT :: {}", userId);
			msg = "insert";
			
			user.setUserId(userId);
		}
		loginProcess(user);
		
		return msg;
	}
/* e:NAVER */

	@Override
	public void loginProcess(UsersVo user) {
		HttpServletRequest request	= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session			= request.getSession();
		LoginSessionVo login		= new LoginSessionVo(user.getUserId(), user.getUserName(), user.getEmail(), user.getNickname(), user.getMobile(), user.getSnsType(), user.getAuthName());
		
		if (session.getAttribute("loginSession") != null) {
			session.invalidate();
		}
		session.setAttribute("loginSession", login);
		Map<String, String> input = Map.of(
			"userId"		, user.getUserId() + "",
			"loginIp"		, Common.getClientIP(),
			"loginDevice"	, Common.getDevice(request),
			"loginType"		, user.getSnsType()
		);
		usersMapper.insertUserLog(input);
	}

	@Override
	public void logout() {
		HttpServletRequest request	= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session			= request.getSession();
		session.invalidate();
	}

	@Override
	public boolean isLogin() {
		LoginSessionVo login = getLoginData();
		return !ObjectUtils.isEmpty(login);
	}

	@Override
	public LoginSessionVo getLoginData() {
		HttpServletRequest request	= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session			= request.getSession();
		LoginSessionVo login		= (LoginSessionVo) session.getAttribute("loginSession");
		return login;
	}
	
	

	
	

}
