package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CommentVo {
	
	private int commId, bordId, userId;
	
	private String commCont;
	
	private Timestamp commDate;
	
	
	private int cnt, isCreated;
	
	private String nickname;

}
