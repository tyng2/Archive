package com.main.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {
	
	private int allRowCount, pageBlockSize;
	// 총 데이터 개수,  페이지블록 크기

	private int pageNum;
	// 현재 페이지
	
	private int startPage, endPage;
	// 현재 페이지블록 시작값, 끝값
	
	private int maxPage;
	// 페이지블록 최대값
	
}
