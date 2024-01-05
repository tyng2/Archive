package com.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.main.vo.MenuVo;

@Mapper
public interface HomeMapper {
	
	public List<MenuVo> getMenuList();

}
