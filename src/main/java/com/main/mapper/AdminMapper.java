package com.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.main.vo.MenuVo;
import com.main.vo.UserAuthVo;
import com.main.vo.UsersVo;

@Mapper
public interface AdminMapper {
	
	public List<UsersVo> getUserList();
	
	public UsersVo getUserById(@Param("userId") String userId);
	
	public int updateUser(UsersVo user);
	
	public List<UserAuthVo> getUserAuth();
	
	public int insertMenu(MenuVo menu);
	
	public int deleteMenu(@Param("menuId") String menuId);
	
	public int updateMenu(MenuVo menu);

}
