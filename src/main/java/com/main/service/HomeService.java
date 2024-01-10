package com.main.service;

import java.util.List;
import java.util.Map;

import com.main.vo.MenuVo;
import com.main.vo.SiteVo;

public interface HomeService {
	
	public List<MenuVo> getMenuList(int auth);
	
	public MenuVo getMenuByLink(String menuLink);
	
	public Map<String, Object> getIndexSlideCont();
	
	public List<SiteVo> getSiteList(int userId);
	
	public int insertSite(SiteVo site);

	public int deleteSite(SiteVo site);
	
}
