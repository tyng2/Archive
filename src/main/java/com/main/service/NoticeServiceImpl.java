package com.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.main.comm.Common;
import com.main.comm.PageSet;
import com.main.mapper.NoticeMapper;
import com.main.vo.NoticeVo;
import com.main.vo.PageInfo;

@Service
public class NoticeServiceImpl implements NoticeService {

	private int NOTI_LIMIT_SIZE, NOTI_PAGE_BLOCK_SIZE;
	
	@Value("${noti.limit}")
	private void setNotiLimit(int noti_limit) {
		NOTI_LIMIT_SIZE = noti_limit;
	}
	
	@Value("${noti.block}")
	private void setNotiBlockSize(int noti_block) {
		NOTI_PAGE_BLOCK_SIZE = noti_block;
	}
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public Map<String, Object> getNoticeList(String pageNum) {
		
		int pageNow		= Common.str2Int(pageNum);
		pageNow			= (pageNow > 0) ? pageNow : 1;
		int startRow	= (pageNow - 1) * NOTI_LIMIT_SIZE;
		
		List<NoticeVo> noticeList = noticeMapper.getNotice(NOTI_LIMIT_SIZE, startRow);
		int allRowCount = 0;
		if (noticeList.size() > 0) {
			allRowCount = (noticeList.get(0) != null) ? noticeList.get(0).getCnt() : 0;
		}
		
		PageInfo pageInfo = PageSet.getPageData(pageNow, allRowCount, NOTI_LIMIT_SIZE, NOTI_PAGE_BLOCK_SIZE);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageInfo"	, pageInfo);
		result.put("noticeList"	, noticeList);
		return result; 
	}

	@Override
	public int insertNotice(NoticeVo notice) {
		return noticeMapper.insertNotice(notice);
	}
	

}
