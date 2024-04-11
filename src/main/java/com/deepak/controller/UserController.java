package com.deepak.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.model.User;

@RestController
public class UserController {
	
	List<User> users = new ArrayList<>();
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return users;
	}
	
	@PostMapping("/users/signup")
	public User createUser(@RequestBody User user) {
		// Checking if user already exists or not
		Integer id = user.getId();
		for(User u : users) {
			if(u.getId() == id) {
				System.out.println("User already exists with id : " + id);
				return null;
			}
		}
		
		// Creating a new user
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		
		// Adding new user into our database record
		users.add(newUser);
		
		return newUser;
	}

}
