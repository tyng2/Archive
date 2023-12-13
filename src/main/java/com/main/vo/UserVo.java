package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserVo {
	
	private int userId;
	
	private String userName, userPswd, email, nickname;
	
	private Timestamp createDate, modifyDate, snsConnectDate;
	
	private String snsType, snsId, snsProfile;
	

}
