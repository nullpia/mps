package com.nullpia.mps.app.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nullpia.mps.app.common.domain.Menu;
import com.nullpia.mps.app.common.mapper.MenuMapper;

@Service
public class MenuService {
	public static String ROOT = "ROOT";
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	public List<Menu> listMenu(Menu menu) {
		return menuMapper.listMenu(menu);
	}
	
	public List<Menu> listChildren(String menuPid) {
		return menuMapper.listChildren(menuPid);
	}

	public int countMenu(Menu menu) {
		return menuMapper.countMenu(menu);
	}

	public Menu selectMenu(String menuId) {
		return menuMapper.selectMenu(menuId);
	}

	public List<Menu> selectPaths(String menuId) {
		List<Menu> paths = new ArrayList<Menu>();
		Menu currMenu = menuMapper.selectMenu(menuId);
		while( currMenu != null) {
			paths.add(0, currMenu);
			currMenu = menuMapper.selectMenu(currMenu.getMenuPid());
		}
		return paths;
	}

	@Transactional
	public Menu insertMenu(Menu menu) {
		menu.setMenuId(sequenceService.getNewSqno(SequenceService.MENU).toString());
		menuMapper.insertMenu(menu);
		return menu;
	}
	
	@Transactional
	public void updateMenu(Menu menu) {
		menuMapper.updateMenu(menu);
	}
	
	@Transactional
	public void deleteMenu(String menuId) {
		menuMapper.deleteMenu(menuId);
	}
}
