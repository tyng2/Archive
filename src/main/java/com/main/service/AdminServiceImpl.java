package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.comm.Common;
import com.main.mapper.AdminMapper;
import com.main.mapper.HomeMapper;
import com.main.mapper.UsersMapper;
import com.main.vo.MenuVo;
import com.main.vo.SiteVo;
import com.main.vo.UserAuthVo;
import com.main.vo.UsersVo;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private HomeMapper homeMapper;

	@Override
	public List<UsersVo> getUserList() {
		return adminMapper.getUserList();
	}
	
	@Override
	public UsersVo getUserById(String userId) {
		return adminMapper.getUserById(userId);
	}
	
	@Override
	public int updateUser(UsersVo user) {
		return adminMapper.updateUser(user);
	}
	
	@Override
	public int deleteUser(String userId) {
		
		int cnt = 0; 
		cnt += usersMapper.deleteUser(userId);
		
		SiteVo site = new SiteVo();
		site.setUserId(Common.str2Int(userId));
		cnt += homeMapper.deleteSite(site);
		
		return cnt;
	}

	@Override
	public List<UserAuthVo> getUserAuth() {
		return adminMapper.getUserAuth();
	}

	@Override
	public int insertMenu(MenuVo menu) {
		return adminMapper.insertMenu(menu);
	}

	@Override
	public int deleteMenu(String menuId) {
		return adminMapper.deleteMenu(menuId);
	}

	@Override
	public int updateMenu(MenuVo menu) {
		return adminMapper.updateMenu(menu);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int switchMenuOrder(MenuVo menuChk, MenuVo menuTar) {
		int cnt = 0;
		cnt += adminMapper.updateMenu(menuChk);
		cnt += adminMapper.updateMenu(menuTar);
		return cnt;
	}
	
	
	
	

}
