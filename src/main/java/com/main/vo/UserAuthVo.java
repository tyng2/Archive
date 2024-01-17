package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserAuthVo {
	
	private int authId;
	
	private String authName;
	
	private Timestamp authDate;

}
