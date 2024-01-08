package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SiteVo {
	
	private int siteId, userId;
	
	private String siteName, siteUrl;
	
	private Timestamp createdDate;

}
