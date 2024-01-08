package com.main.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.main.mapper.HomeMapper;
import com.main.vo.MenuVo;
import com.main.vo.SiteVo;

@Service
public class HomeServiceImpl implements HomeService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HomeMapper homeMapper;
	
	@Cacheable(value = "menu", key = "#p0", unless = "#result == null")
	@Override
	public List<MenuVo> getMenuList(int auth) {
		List<Integer> authList = new ArrayList<Integer>();
		for (int i = 3; i >= auth; i--) {
			authList.add(i);
		}
		log.info("AUTHLIST :: {}", authList.toString());
		return homeMapper.getMenuList(authList);
	}

	@Override
	public List<SiteVo> getSiteList(int userId) {
		return homeMapper.getSiteList(userId);
	}

	@Override
	public int insertSite(SiteVo site) {
		return homeMapper.insertSite(site);
	}

	@Override
	public int deleteSite(SiteVo site) {
		return homeMapper.deleteSite(site);
	}
	

}
