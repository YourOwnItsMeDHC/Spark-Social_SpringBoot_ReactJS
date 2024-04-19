package com.deepak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.deepak.model.User;
import com.deepak.repository.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());

		userRepository.save(newUser);

		return newUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return user.get();
		}

		throw new Exception("User not exists with an id : " + userId);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer user1, Integer user2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> searchUser(String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
