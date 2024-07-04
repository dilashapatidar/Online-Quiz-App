package com.project.Online_Quiz_App.entities;

import org.springframework.stereotype.Component;

@Component
public class Admin {
	
	private String id;
	
	private String hashedPassword;
	
	private String salt;


	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String id, String password, String salt) {
		super();
		this.id = id;
		this.hashedPassword = password;
		this.salt = salt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
}
