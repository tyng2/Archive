package com.main.comm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.main.vo.PageInfo;

@Component
public class PageSet {
	
	private static int LIMIT_SIZE, PAGE_BLOCK_SIZE;
	// 한 페이지당 글 개수, 페이지 블록 개수
	
	@Value("${page.limit}")
	private void setLimit(int page_limit) {
		LIMIT_SIZE = page_limit;
	}
	
	@Value("${page.block}")
	private void setBlockSize(int page_block) {
		PAGE_BLOCK_SIZE = page_block;
	}
	
	public static PageInfo getPageData(int pageNum, int allRowCount) {
		
		int maxPage		= allRowCount / LIMIT_SIZE + ((allRowCount % LIMIT_SIZE != 0) ? 1 : 0);
		int startPage	= (pageNum / PAGE_BLOCK_SIZE - ((pageNum % PAGE_BLOCK_SIZE == 0) ? 1 : 0)) * PAGE_BLOCK_SIZE + 1;
		int endPage		= startPage + PAGE_BLOCK_SIZE - 1;
		endPage			= (endPage > maxPage) ? maxPage : endPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setAllRowCount(allRowCount);
		pageInfo.setPageBlockSize(PAGE_BLOCK_SIZE);
		pageInfo.setPageNum(pageNum);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setMaxPage(maxPage);
		
		return pageInfo;
	}

}
