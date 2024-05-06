package com.deepak.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.model.Post;
import com.deepak.model.User;
import com.deepak.repository.PostRepository;
import com.deepak.repository.UserRepository;
import com.deepak.response.ApiResponse;
import com.deepak.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/posts/user/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception {
		Post createdPost = postService.createNewPost(post, userId);
		return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/posts/{postId}/user/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
		String message = postService.deletePost(postId, userId);
		ApiResponse res = new ApiResponse(message, true);
		
		String comparingMessage = "Post with an id : " + postId + " has been deleted successfully";
		Post post = postService.findPostById(postId);
		if(message.equals(comparingMessage)) {
			
			// Check if there are any related records in users_saved_post
			//There can be possibility that the post which I am deleting has been saved by some others as well
			List<User> users = userRepository.findAll();
			
			Iterator<User> it = users.iterator();
			while(it.hasNext()) {
				User user = it.next();
				if(user.getSavedPost().contains(post)) {
					user.getSavedPost().remove(post);
					userRepository.save(user);
				}
			}
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		message = "These post : " + postId + " is saved by some other users as well, hence, can't delete it";
		res.setMessage(message);
		res.setStatus(false);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
		Post post = postService.findPostById(postId);
		return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) throws Exception {
		List<Post> posts = postService.findPostByUserId(userId);
		return new ResponseEntity<>(posts, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPost() throws Exception {
		List<Post> posts = postService.findAllPost();
		return new ResponseEntity<>(posts, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/posts/save/{postId}/user/{userId}")
	public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
		Post post = postService.savedPost(postId, userId);
		return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/posts/like/{postId}/user/{userId}") 
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
		Post post = postService.likePost(postId, userId);
		return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
	}
	
	
}
