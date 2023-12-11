package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class FileVo {
	
	private int file_id, bord_id;
	
	private String file_olnm, file_svnm;
	
	private Timestamp file_date;

}
