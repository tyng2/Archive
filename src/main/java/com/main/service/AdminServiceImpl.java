package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.mapper.AdminMapper;
import com.main.vo.MenuVo;
import com.main.vo.UsersVo;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public List<UsersVo> getUserList() {
		return adminMapper.getUserList();
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
