package com.deepak.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.model.User;
import com.deepak.repository.UserRepository;
import com.deepak.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception {
		User user = userService.findUserById(id);
		return user;
	}

	@PostMapping("/users/signup")
	public User createUser(@RequestBody User user) {
		User newUser = userService.registerUser(user);
		return newUser;
		
	}

	/*
	 * { "id": 1, "firstName": "Deepak", "lastName": "Chourasiya", "email":
	 * "deepak@gmail.com", "password": "deepu1234" }
	 */

	@PutMapping("users/{userId}")
	public User updateUser(@PathVariable("userId") Integer id , @RequestBody User user) throws Exception {
		User updatedUser = userService.updateUser(user, id);
		return updatedUser;
	}

	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable("userId") Integer id) throws Exception {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new Exception("User not exists with an id : " + id); 
		}
		
		userRepository.delete(user.get());
		
		return "User deleted successfully with an id : " + id;
	}
	
	@PutMapping("users/follow/{userId1}/{userId2}")
	public User followUserHandler(@PathVariable("userId1") Integer userId1, @PathVariable("userId2") Integer userId2) throws Exception {
		User user = userService.followUser(userId1, userId2);
		return user;
	}
	
	@GetMapping("users/search")
	public List<User> searchUser(@RequestParam("query") String query) {
		List<User> users = userService.searchUser(query);
		return users;
	}

}
