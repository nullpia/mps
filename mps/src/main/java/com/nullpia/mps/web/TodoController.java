package com.nullpia.mps.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nullpia.mps.app.blog.service.PostService;

@Controller
public class TodoController {
    private static final Logger log = LoggerFactory.getLogger(TodoController.class);
    
    @Autowired
    PostService postService;
    
    @RequestMapping("/router/todo")
   	public String todo(Model model) {
    	return "/router/todo";
   	}
}
