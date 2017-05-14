package com.nullpia.mps.app.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nullpia.mps.app.common.domain.Menu;
import com.nullpia.mps.app.common.mapper.MenuMapper;

@Service
public class MenuService {
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	public List<Menu> listMenu(Menu menu) {
		return menuMapper.listMenu(menu);
	}

	public int countMenu(Menu menu) {
		return menuMapper.countMenu(menu);
	}

	public Menu selectMenu(String menuId) {
		return menuMapper.selectMenu(menuId);
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
