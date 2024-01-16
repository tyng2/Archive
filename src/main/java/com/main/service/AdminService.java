package com.main.service;

import java.util.List;

import com.main.vo.MenuVo;
import com.main.vo.UsersVo;

public interface AdminService {
	
	public List<UsersVo> getUserList();
	
	public int insertMenu(MenuVo menu);
	
	public int deleteMenu(String menuId);
	
	public int updateMenu(MenuVo menu);
	
	public int switchMenuOrder(MenuVo menuChk, MenuVo menuTar);

}
