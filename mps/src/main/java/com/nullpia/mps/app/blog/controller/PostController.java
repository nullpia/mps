package com.nullpia.mps.app.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nullpia.mps.app.blog.domain.Post;
import com.nullpia.mps.app.blog.service.PostService;

@Controller
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("/api/post/list")
	@ResponseBody
	public List<Post> listPost(@RequestBody Post post) {
		return postService.listPost(post);
	}

	@RequestMapping("/api/post/view/{postId}")
	@ResponseBody
	public Post viewPost(@PathVariable int postId) {
		postService.updateReadCount(postId);
		
		return postService.selectPost(postId);
	}

	@RequestMapping("/api/post/new")
	@ResponseBody
	public Post newPost(@RequestBody Post post) {
		return postService.insertPost(post);
	}

}
