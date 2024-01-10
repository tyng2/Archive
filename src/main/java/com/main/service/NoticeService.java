package com.main.service;

import java.util.Map;

import com.main.vo.NoticeVo;

public interface NoticeService {
	
	public Map<String, Object> getNoticeList(String pageNum);
	
	public int insertNotice(NoticeVo notice);

}
