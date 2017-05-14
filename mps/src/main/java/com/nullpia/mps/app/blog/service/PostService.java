package com.nullpia.mps.app.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nullpia.mps.app.blog.domain.Post;
import com.nullpia.mps.app.blog.mapper.PostMapper;
import com.nullpia.mps.app.common.service.SequenceService;

@Service
public class PostService {
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	public List<Post> listPost(Post post) {
		return postMapper.listPost(post);
	}

	public int countPost(Post post) {
		return postMapper.countPost(post);
	}

	public Post selectPost(int postId) {
		return postMapper.selectPost(postId);
	}
	
	@Transactional
	public Post insertPost(Post post) {
		post.setPostId(sequenceService.getNewSqno(SequenceService.POST).intValue());
		
		postMapper.insertPost(post);
		
		return post;
	}
	
	@Transactional
	public void updatePost(Post post) {
		postMapper.updatePost(post);
	}
	
	@Transactional
	public void updateReadCount(int postId) {
		postMapper.updateReadCount(postId);
	}
	
	@Transactional
	public void deletePost(int postId) {
		postMapper.deletePost(postId);
	}
}
