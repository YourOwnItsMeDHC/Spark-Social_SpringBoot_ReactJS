package com.deepak.model;



import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String gender;
	
	private Set<Integer> followings;
	private Set<Integer> followers;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String firstName, String lastName, String email, String password, String gender,
			Set<Integer> followings, Set<Integer> followers) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.followings = followings;
		this.followers = followers;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Set<Integer> getFollowings() {
		return followings;
	}

	public void setFollowings(Set<Integer> followings) {
		this.followings = followings;
	}

	public Set<Integer> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<Integer> followers) {
		this.followers = followers;
	}
	
	
}
