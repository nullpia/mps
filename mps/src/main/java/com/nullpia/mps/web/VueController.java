package com.nullpia.mps.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nullpia.mps.api.blog.domain.Post;
import com.nullpia.mps.api.blog.service.PostService;
import com.nullpia.mps.utility.Dates;

@Controller
public class VueController {
    private static final Logger log = LoggerFactory.getLogger(VueController.class);

    @RequestMapping("/router/vuelist")
   	public String list(Model model) {
    	return "/router/vuelist";
   	}
    
    
}
