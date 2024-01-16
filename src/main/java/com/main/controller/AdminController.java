package com.main.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.comm.Common;
import com.main.service.AdminService;
import com.main.vo.MenuVo;
import com.main.vo.UsersVo;

@Controller
public class AdminController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin")
	public String admin() {
		log.info("admin page");
		return "admin/admin";
	}
	
	@GetMapping("/userList")
	public String userAdmin(Model model) {
		
		List<UsersVo> userList = adminService.getUserList();
		model.addAttribute("userList", userList);
		return "admin/userList";
	}
	
	@CacheEvict(value = "menu", allEntries = true)
	@GetMapping("/menuList")
	public String menuAdmin(Model model) {
		log.info("menu List");
		
		return "admin/menuList";
	}
	
	@ResponseBody
	@CacheEvict(value = "menu", allEntries = true)
	@PostMapping("/insertMenu")
	public int insertMenu(@RequestParam Map<String, String> paramMap) {
		String menuName		= Common.nvl(paramMap.get("menuName"));
		String menuLink		= Common.nvl(paramMap.get("menuLink"));
		int rdAuth			= Common.str2Int(paramMap.get("rdAuth"));
		int wrAuth			= Common.str2Int(paramMap.get("wrAuth"));
		int menuParent		= Common.str2Int(paramMap.get("menuParent"));
		
		MenuVo menu			= new MenuVo();
		menu.setMenuName(menuName);
		menu.setMenuLink(menuLink);
		menu.setRdAuth(rdAuth);
		menu.setWrAuth(wrAuth);
		menu.setMenuParent(menuParent);
		
		int cnt = adminService.insertMenu(menu);
		return cnt;
	}
	
	@ResponseBody
	@CacheEvict(value = "menu", allEntries = true)
	@PostMapping("/deleteMenu")
	public int deleteMenu(@RequestParam Map<String, String> paramMap) {
		
		String menuId	= Common.nvl(paramMap.get("menuId"));
		int cnt			= adminService.deleteMenu(menuId);
		
		return cnt;
	}
	
	@ResponseBody
	@CacheEvict(value = "menu", allEntries = true)
	@PostMapping("/modifyMenu")
	public int modifyMenu(@RequestParam Map<String, String> paramMap) {
		log.info("MODIFY MENU :: {}", paramMap.toString());
		
		String menuName		= Common.nvl(paramMap.get("menuName"));
		String menuLink		= Common.nvl(paramMap.get("menuLink"));
		int rdAuth			= Common.str2Int(paramMap.get("rdAuth"));
		int wrAuth			= Common.str2Int(paramMap.get("wrAuth"));
		int menuId			= Common.str2Int(paramMap.get("menuId"));
		
		MenuVo menu			= new MenuVo();
		menu.setMenuName(menuName);
		menu.setMenuLink(menuLink);
		menu.setRdAuth(rdAuth);
		menu.setWrAuth(wrAuth);
		menu.setMenuId(menuId);
		
		int cnt = adminService.updateMenu(menu);
		return cnt;
	}
	
	@ResponseBody
	@CacheEvict(value = "menu", allEntries = true)
	@PostMapping("/modifyMenuOrder")
	public int modifyMenuOrder(@RequestParam Map<String, String> paramMap) {
		log.info("MODIFY MENU :: {}", paramMap.toString());
		
		int menuIdChk	= Common.str2Int(paramMap.get("menuIdChk"));
		int newOrderChk	= Common.str2Int(paramMap.get("newOrderChk"));
		int menuIdTar	= Common.str2Int(paramMap.get("menuIdTar"));
		int newOrderTar	= Common.str2Int(paramMap.get("newOrderTar"));
		
		MenuVo menuChk	= new MenuVo();
		menuChk.setMenuId(menuIdChk);
		menuChk.setMenuOrder(newOrderChk);
		MenuVo menuTar	= new MenuVo();
		menuTar.setMenuId(menuIdTar);
		menuTar.setMenuOrder(newOrderTar);
		
		int cnt = adminService.switchMenuOrder(menuChk, menuTar);
		
		return cnt;
	}
	
	
	

}
