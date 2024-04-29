package com.deepak.service;

import java.util.List;

import com.deepak.model.Post;

public interface PostService {
	
	public Post createNewPost(Post post, Integer userId) throws Exception;
	
	public String deletePost(Integer postId, Integer userId) throws Exception;
	
	public List<Post> findPostByUserId (Integer userId) throws Exception;
	
	public Post findPostById(Integer postId) throws Exception;
	
	public List<Post> findAllPost();
	
	public Post savedPost(Integer postId, Integer userId) throws Exception;
	
	public Post unsavedPost(Integer postId, Integer userId) throws Exception;
	
	public Post likePost(Integer postId, Integer userId) throws Exception;
}
