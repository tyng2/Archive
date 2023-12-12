package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CommentVo {
	
	private int comm_id, bord_id;
	
	private String user_id, comm_cont;
	
	private Timestamp comm_date;

}
