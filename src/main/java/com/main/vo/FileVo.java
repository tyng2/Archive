package com.main.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class FileVo {
	
	private int fileId, bordId;
	
	private String fileOlnm, fileSvnm;
	
	private Timestamp fileDate;

}
