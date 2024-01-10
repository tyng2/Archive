package com.main.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.comm.Common;
import com.main.comm.SessionUtil;
import com.main.service.NoticeService;
import com.main.vo.LoginSessionVo;
import com.main.vo.NoticeVo;
import com.main.vo.PageInfo;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class NoticeController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/notice")
	public String notice(@RequestParam Map<String, String> paramMap, Model model) {
		log.info("notice");
		
		String pageNum = Common.nvl(paramMap.get("pageNum"), "1");
		
		Map<String, Object> result	= noticeService.getNoticeList(pageNum);
		
		List<NoticeVo> noticeList	= (List<NoticeVo>) result.get("noticeList");
		PageInfo pageInfo			= (PageInfo) result.get("pageInfo");
		model.addAttribute("noticeList"	, noticeList);
		model.addAttribute("pageInfo"	, pageInfo);
		return "notice/notice";
	}
	
	@GetMapping("/noticeWrite")
	public String noticeWrite(@RequestParam Map<String, String> paramMap, Model model) {
		
		return "notice/noticeWrite";
	}
	
	@PostMapping("/insertNotice")
	public String insertNotice(HttpServletRequest requset, @RequestParam Map<String, String> paramMap) {
		log.info("insert notice");
		
		LoginSessionVo login = SessionUtil.getLoginData(requset);
		
		if (login != null) {
			String notiTitle	= paramMap.get("notiTitle");
			String notiCont		= paramMap.get("notiCont");
			
			NoticeVo notice = new NoticeVo();
			notice.setUserId(login.getUserId());
			notice.setNotiTitle(notiTitle);
			notice.setNotiCont(notiCont);
			
			noticeService.insertNotice(notice);
		}
		return "redirect:/notice";
	}
	
}
