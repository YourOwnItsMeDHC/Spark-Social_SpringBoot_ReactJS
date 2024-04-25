package com.deepak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepak.model.User;
import com.deepak.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

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
		newUser.setGender(user.getGender());

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
	public User followUser(Integer userId1, Integer userId2) throws Exception {
//		userId1 wants to follow userId2
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);

		if(user1.getFollowings().contains(user2.getId())) {
			user1.getFollowings().remove(userId2);
			user2.getFollowers().remove(userId1);
		}
		else {
			user1.getFollowings().add(userId2);
			user2.getFollowers().add(userId1);
		}

		userRepository.save(user1);
		userRepository.save(user2);

		return user1;
	}

	@Override
	public User unFollowUser(Integer userId1, Integer userId2) throws Exception {
//		userId1 wants to unfollow userId2
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);

		user1.getFollowings().remove(userId2);
		user2.getFollowers().remove(userId1);

		userRepository.delete(user1);
		userRepository.delete(user2);

		return user1;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		Optional<User> oldUser = userRepository.findById(userId);

		if (oldUser.isEmpty()) {
			throw new Exception("User not exists with an id : " + userId);
		}

		if (user.getFirstName() != null) {
			oldUser.get().setFirstName(user.getFirstName());
		} else if (user.getLastName() != null) {
			oldUser.get().setLastName(user.getLastName());
		} else if (user.getEmail() != null) {
			oldUser.get().setEmail(user.getEmail());
		} else if (user.getPassword() != null) {
			oldUser.get().setPassword(user.getPassword());
		}

		userRepository.save(oldUser.get());

		return oldUser.get();
	}

	@Override
	public List<User> searchUser(String query) {
		List<User> users = userRepository.searchUser(query);
		return users;
	}

}
