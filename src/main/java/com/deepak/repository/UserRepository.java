package com.deepak.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepak.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
