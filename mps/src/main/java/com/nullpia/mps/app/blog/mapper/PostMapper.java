package com.nullpia.mps.app.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nullpia.mps.app.blog.domain.Post;

@Mapper
public interface PostMapper {
	
	public java.util.List<Post> listPost(Post post);
	public int countPost(Post post);
	public Post selectPost(int postId);
	public void insertPost(Post post);
	public void updatePost(Post post);
	public void updateReadCount(long postId);
	public void deletePost(int postId);
	
}
	