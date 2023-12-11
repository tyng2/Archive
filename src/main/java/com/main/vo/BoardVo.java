package com.main.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVo {
	
	private int bord_id, bord_catg;
	private String catg_name;
	private String bord_titl, bord_cont, user_id, bord_wrip;
	private Timestamp bord_date;
	private int bord_hitc, cnt;

}
