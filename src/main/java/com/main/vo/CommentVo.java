package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CommentVo {
	
	private int commId, bordId;
	
	private String userId, commCont;
	
	private Timestamp commDate;

}
