package com.nullpia.mps.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nullpia.mps.app.blog.domain.Post;
import com.nullpia.mps.app.blog.service.PostService;
import com.nullpia.mps.utility.Dates;

@Controller
public class BlogController {
    private static final Logger log = LoggerFactory.getLogger(BlogController.class);
    
    @Autowired
    PostService postService;
    
    @RequestMapping("/router/blogs")
   	public String list(Model model) {
    	
    	List<Post> posts = postService.listPost( new Post() );
    	for( Post post : posts ) {
    		post.setCreDttm(Dates.format(post.getCreDttm(),"yyyy.MM.dd HH:mm:ss"));
    	}
    	model.addAttribute("posts", posts);
    	return "/router/blogs";
   	}
    
    
    @RequestMapping("/router/blogview/{id}")
   	public String viewPost(@PathVariable int id, Model model) {
    	
    	Post post = postService.selectPost(id);
    	post.setCreDttm(Dates.format(post.getCreDttm(),"yyyy.MM.dd HH:mm:ss"));
    	
    	model.addAttribute("post", post);
    	return "/router/blogview";
   	}
    
    @RequestMapping("/router/blognew")
   	public String newPost(Model model) {
    	return "/router/blognew";
   	}
    
}
