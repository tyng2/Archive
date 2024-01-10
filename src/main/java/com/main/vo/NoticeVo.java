package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class NoticeVo {
	
	private int notiId, userId;
	
	private String notiTitle, notiCont;
	
	private Timestamp createdDate;
	
	private int cnt;

}
