package com.viscum.entity;

public class User {

	private String userId;
	private String username;
	private String userphone;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	@Override
	public String toString() {
		return "user{" +
				"userId='" + userId + '\'' +
				", username='" + username + '\'' +
				", userphone='" + userphone + '\'' +
				'}';
	}
}
