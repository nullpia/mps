package com.nullpia.mps.app.common.domain;

import java.util.List;

public class Menu {
	private String menuId;
	private String menuPid;
	private String resourceId;
	private String menuNm;
	private int lvlNo;
	private int srtNo;
	private String creUsrId;
	private String creDttm;
	private String modUsrId;
	private String modDttm;
	
	private Menu parent;
	private List<Menu> children;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuPid() {
		return menuPid;
	}
	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public int getLvlNo() {
		return lvlNo;
	}
	public void setLvlNo(int lvlNo) {
		this.lvlNo = lvlNo;
	}
	public int getSrtNo() {
		return srtNo;
	}
	public void setSrtNo(int srtNo) {
		this.srtNo = srtNo;
	}
	public String getCreUsrId() {
		return creUsrId;
	}
	public void setCreUsrId(String creUsrId) {
		this.creUsrId = creUsrId;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
	public String getModUsrId() {
		return modUsrId;
	}
	public void setModUsrId(String modUsrId) {
		this.modUsrId = modUsrId;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
		for (Menu child : children) {
			child.parent = this;
		}
	}
	

	
}
