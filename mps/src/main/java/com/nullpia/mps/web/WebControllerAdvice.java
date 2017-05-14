package com.nullpia.mps.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.nullpia.mps.app.common.domain.Menu;
import com.nullpia.mps.app.common.service.MenuService;

/**
 * Controller의 공통 Model 및 예외처리를 한다
 * @author nullpia
 *
 */
@ControllerAdvice
public class WebControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(WebControllerAdvice.class);
	
	 @Autowired
	 MenuService menuService;
	 
	 @ModelAttribute
	 public void globalAttributes(HttpServletRequest request, HttpServletResponse response, Model model) {
		 String uri = request.getRequestURI();
		
		 // 현재 접속한 리소스에 대한 메뉴/사용자 정보를 설정한다.
		 if( StringUtils.hasLength(uri) && uri.startsWith("/router/")) {
			 String menuId = uri.substring(8);
			 model.addAttribute("router", menuId);
			 
			 Menu menu = menuService.selectMenu(menuId);
			 model.addAttribute("menu", menu);
			 
			 List<Menu> paths = menuService.selectPaths(menuId);
			 model.addAttribute("paths", paths);
			 
			 List<Menu> menus = menuService.listMenu(new Menu());
			 model.addAttribute("menus", menus);
			 	 
			 //TODO 계층 구조 메뉴를 구성한다.
			 logger.info("########### 계층 ###############");
			 Menu home = menuService.selectMenu("index");
			 recursiveMenus(home);
			 model.addAttribute("recursiveMenu", home);
			 
		 }
	 }
	 
	 private void recursiveMenus(Menu parent) {
		 List <Menu> children = menuService.listChildren(parent.getMenuId());
		 parent.setChildren(children);
		 
		 for( Menu child : children) {
			 recursiveMenus(child);
		 }
	 }
}
