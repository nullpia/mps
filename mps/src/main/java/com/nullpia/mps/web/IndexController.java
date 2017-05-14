package com.nullpia.mps.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
	public String index(Model model) {
		return "redirect:/router/index";
	}
    
    @RequestMapping("/router/index")
	public String routeIndex(Model model, HttpServletRequest request) {
		return "/router/index";
	}

}
