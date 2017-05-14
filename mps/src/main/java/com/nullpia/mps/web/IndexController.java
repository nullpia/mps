package com.nullpia.mps.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nullpia.mps.app.common.domain.Menu;
import com.nullpia.mps.app.common.service.MenuService;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    MenuService menuService;
    
    @RequestMapping("/")
	public String index(Model model) {
		return "redirect:/router/index";
	}
    
    @RequestMapping("/router/index")
	public String routeIndex(Model model, HttpServletRequest request) {
    	Menu menu = menuService.selectMenu("index");
    	logger.info("resourceId = {}", menu.getResourceId());
    	return menu.getResourceId();
	}

}
