package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UsersVo {
	
	private int userId, authId;
	
	private String userTxid, userName, userPswd, email, nickname, mobile;
	
	private Timestamp createDate, modifyDate, snsConnectDate;
	
	private String snsType, snsId, snsProfile;
	
	
	private String authName;

}
