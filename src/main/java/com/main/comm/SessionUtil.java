package com.main.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import com.main.vo.LoginSessionVo;
import com.main.vo.UsersVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {

	private static Logger log = LoggerFactory.getLogger(SessionUtil.class);
	
	public static LoginSessionVo getLoginData(HttpServletRequest request) {
		HttpSession session		= request.getSession();
		LoginSessionVo login	= (LoginSessionVo) session.getAttribute("loginSession");
		return login;
	}
	
	public static boolean isLogin(HttpServletRequest request) {
		LoginSessionVo login = getLoginData(request);
		return (login != null);
	}
	
	public static void logout(HttpServletRequest request) {
		HttpSession session			= request.getSession();
		session.invalidate();
	}
	
	public static boolean login(HttpServletRequest request, UsersVo user) {
		boolean result			= false;
		HttpSession session		= request.getSession();
		
		if (user != null && !ObjectUtils.isEmpty(user)) {
			LoginSessionVo login	= new LoginSessionVo(user.getUserId(), user.getAuthId(), user.getUserName(), user.getEmail(), user.getNickname(), user.getMobile(), user.getSnsType(), user.getAuthName());
			
			if (session.getAttribute("loginSession") != null) {
				session.invalidate();
			}
			session.setAttribute("loginSession", login);
			result = !ObjectUtils.isEmpty(login); 
		}
		return result;
	}
	
	
	
}
