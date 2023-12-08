package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVo {
	
	int bord_id, bord_catg;
	String catg_name;
	String bord_titl, bord_cont, user_id, bord_wrip;
	Timestamp bord_date;
	int bord_hitc, cnt;

}
