package com.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.main.vo.MenuVo;
import com.main.vo.UsersVo;

@Mapper
public interface AdminMapper {
	
	public List<UsersVo> getUserList();
	
	public int insertMenu(MenuVo menu);
	
	public int deleteMenu(@Param("menuId") String menuId);
	
	public int updateMenu(MenuVo menu);

}
