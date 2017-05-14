package com.nullpia.mps.app.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nullpia.mps.app.common.domain.Menu;

@Mapper
public interface MenuMapper {
	
	public java.util.List<Menu> listMenu(Menu menu);
	public java.util.List<Menu> listChildren(String menuPid);
	public int countMenu(Menu menu);
	public Menu selectMenu(String menuId);
	public void insertMenu(Menu menu);
	public void updateMenu(Menu menu);
	public void deleteMenu(String menuId);
	
}
	