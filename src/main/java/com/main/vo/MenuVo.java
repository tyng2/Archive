package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MenuVo {
	
	private int menuId, menuParent, menuOrder, rdAuth, wrAuth;
	
	private String menuName, menuLink, useYn;
	
	private Timestamp createdDate;
	
	
	private int depth, childCnt;

}
