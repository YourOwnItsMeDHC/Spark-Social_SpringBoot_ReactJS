package com.deepak.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.model.User;

@RestController
public class UserController {
	
	@GetMapping("/users")
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		
		User user1 = new User("Deepak", "Chourasiya", "deepak@gmail.com", "deepu1234");
		User user2 = new User("Suraj", "Chourasiya", "suraj@gmail.com", "surya1234");
		
		users.add(user1);
		users.add(user2);
		
		return users;
	}

}
