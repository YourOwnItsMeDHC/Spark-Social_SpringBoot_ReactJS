package com.deepak.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepak.model.Post;
import com.deepak.model.User;
import com.deepak.repository.PostRepository;
import com.deepak.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		User user = userService.findUserById(userId);
		
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		
		postRepository.save(newPost);		
		return newPost;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(post.getUser().getId() != user.getId()) {
			throw new Exception("You are attempting to delete another user's post");
		}
		
		postRepository.delete(post);
		
		return "Post with an id : " + postId + " has been deleted successfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) throws Exception {
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> post = postRepository.findById(postId);
		if(post.isEmpty()) {
			throw new Exception("Post not found with an id : " + postId);
		}
		return post.get();
	}

	@Override
	public List<Post> findAllPost() {
		return postRepository.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}
		else user.getSavedPost().add(post);
		
		userRepository.save(user);
		
		return post;
	}

	@Override
	public Post unsavedPost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}
		else user.getSavedPost().add(post);
		
		userRepository.save(user);
		
		return null;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {
			post.getLiked().add(user);
		}
		
		postRepository.save(post);
		return post;
	}
}
