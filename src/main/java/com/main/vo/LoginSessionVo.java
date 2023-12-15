package com.main.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginSessionVo {
	
	private int userId;
	
	private String userName, email, nickname, mobile, type, userAuth;
	
}
