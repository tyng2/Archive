package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.main.mapper.HomeMapper;
import com.main.vo.MenuVo;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private HomeMapper homeMapper;
	
	@Cacheable(value = "menu")
	@Override
	public List<MenuVo> getMenuList() {
		return homeMapper.getMenuList();
	}

}
