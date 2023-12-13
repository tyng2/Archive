package com.main.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVo {
	
	private int bordId, bordCatg;
	private String catgName;
	private String bordTitl, bordCont, userId, bordWrip;
	private Timestamp bordDate;
	private int bordHitc, cnt, fileCnt, commentCnt;

}
