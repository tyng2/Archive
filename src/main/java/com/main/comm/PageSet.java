package com.main.comm;

import org.springframework.stereotype.Component;

import com.main.vo.PageInfo;

@Component
public class PageSet {
	
	public static PageInfo getPageData(int pageNum, int allRowCount, int limitSize, int pageBlockSize) {
		
		int maxPage		= allRowCount / limitSize + ((allRowCount % limitSize != 0) ? 1 : 0);
		int startPage	= (pageNum / pageBlockSize - ((pageNum % pageBlockSize == 0) ? 1 : 0)) * pageBlockSize + 1;
		int endPage		= startPage + pageBlockSize - 1;
		endPage			= (endPage > maxPage) ? maxPage : endPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setAllRowCount(allRowCount);
		pageInfo.setPageBlockSize(pageBlockSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setMaxPage(maxPage);
		
		return pageInfo;
	}

}
