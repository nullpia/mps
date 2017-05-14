package com.nullpia.mps.api.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nullpia.mps.api.common.domain.Menu;

@Mapper
public interface MenuMapper {
	
	public java.util.List<Menu> listMenu(Menu menu);
	public int countMenu(Menu menu);
	public Menu selectMenu(String menuId);
	public void insertMenu(Menu menu);
	public void updateMenu(Menu menu);
	public void deleteMenu(String menuId);
	
}
	