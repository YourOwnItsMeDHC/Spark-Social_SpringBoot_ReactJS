package com.deepak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@GetMapping("/")
	public String getUsers() {
		return "Hey everyone your own it's me Deepak Chourasiya";
	}
}
