package com.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.main.vo.MenuVo;
import com.main.vo.SiteVo;

@Mapper
public interface HomeMapper {
	
	public List<MenuVo> getMenuList(@Param("authList") List<Integer> authList);
	
	public MenuVo getMenuByLink(@Param("menuLink") String menuLink);
	
	public List<SiteVo> getSiteList(@Param("userId") int userId);
	
	public int insertSite(SiteVo site);

	public int deleteSite(SiteVo site);
	
}
