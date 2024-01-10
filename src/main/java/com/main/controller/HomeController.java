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
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.comm.Common;
import com.main.comm.SessionUtil;
import com.main.service.HomeService;
import com.main.vo.BoardVo;
import com.main.vo.LoginSessionVo;
import com.main.vo.NoticeVo;
import com.main.vo.SiteVo;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private HomeService homeService;
	
	@GetMapping("/")
	public String index(HttpServletRequest request, Model model) {
		boolean isLogin = SessionUtil.isLogin(request);
		log.info("{}",isLogin);
		
		
		Map<String, Object> result	= homeService.getIndexSlideCont();
		
		@SuppressWarnings("unchecked")
		List<BoardVo> boardList		= (List<BoardVo>) result.get("boardList");
		@SuppressWarnings("unchecked")
		List<NoticeVo> noticeList	= (List<NoticeVo>) result.get("noticeList");
		
		model.addAttribute("boardList"	, boardList);
		model.addAttribute("noticeList"	, noticeList);
		return "index";
	}
	
	@ResponseBody
	@GetMapping("/siteList")
	public List<SiteVo> getSiteList(HttpServletRequest request) {
		
		List<SiteVo> siteList = null;
		if (SessionUtil.isLogin(request)) {
			LoginSessionVo login = SessionUtil.getLoginData(request);
			siteList = homeService.getSiteList(login.getUserId());
		}
		return siteList;
	}
	
	@ResponseBody
	@PostMapping("insertSite")
	public int insertSite(HttpServletRequest request, @RequestParam Map<String, String> paramMap) {
		int cnt = 0;
		if (SessionUtil.isLogin(request)) {
			LoginSessionVo login = SessionUtil.getLoginData(request);
			SiteVo site = new SiteVo();
			site.setUserId(login.getUserId());
			site.setSiteName(Common.nvl(paramMap.get("name")));
			site.setSiteUrl(Common.nvl(paramMap.get("url")));
			cnt = homeService.insertSite(site);
		}
		log.info("INSERT CNT :: {}", cnt);
		return cnt;
	}
	
	@ResponseBody
	@PostMapping("deleteSite")
	public int deleteSite(HttpServletRequest request, @RequestParam Map<String, String> paramMap) {
		int cnt = 0;
		if (SessionUtil.isLogin(request)) {
			LoginSessionVo login = SessionUtil.getLoginData(request);
			SiteVo site = new SiteVo();
			site.setUserId(login.getUserId());
			site.setSiteId(Common.str2Int(paramMap.get("siteId")));
			cnt = homeService.deleteSite(site);
		}
		log.info("DELETE CNT :: {}", cnt);
		return cnt;
	}
	
}
