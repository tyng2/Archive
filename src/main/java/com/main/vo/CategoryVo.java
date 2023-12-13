package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CategoryVo {
	
	private int catgId;
	private String catgName;
	private Timestamp catgCrdt;

}
