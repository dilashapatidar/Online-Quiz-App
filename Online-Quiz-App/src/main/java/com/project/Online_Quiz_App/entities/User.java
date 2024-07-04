package com.project.Online_Quiz_App.entities;

import org.springframework.stereotype.Component;

@Component
public class User {
	private String username;
	private String hashedPassword;
	private String salt;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, String salt) {
		super();
		this.username = username;
		this.hashedPassword = password;
		this.salt = salt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String password) {
		this.hashedPassword = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + hashedPassword + ", salt=" + salt + "]";
	}
	
}
