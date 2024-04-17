package com.shopify.api.message.admin.user;

public class AdminUserUpdateResponse {
	private String username;
	
	private String Error;

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
