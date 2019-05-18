package com.exam.domain;

public class User {
	private String id;
	private String password;
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + "]";
	}
	public User() {
		
	}
	public User(String id,String password) {
		this.id = id;
		this.password = password;
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
