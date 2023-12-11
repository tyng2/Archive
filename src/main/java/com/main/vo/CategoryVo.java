package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CategoryVo {
	
	private int catg_id;
	private String catg_name;
	private Timestamp catg_crdt;

}
