package com.nullpia.mps.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VueController {
    private static final Logger logger = LoggerFactory.getLogger(VueController.class);

    @RequestMapping("/router/vuelist")
   	public String list(Model model) {
    	return "/router/vuelist";
   	}
    
    
}
