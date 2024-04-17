package com.shopify.api.message.admin.user;

import java.util.List;

import com.shopify.api.models.role.Role;
import com.shopify.api.models.user.UserEntity;

public class AdminUserCreateResponse {
	private String username;
	private String phone;
	private List<Role> roles;
	private String Error;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getError() {
		return Error;
	}
	public void setError(String error) {
		Error = error;
	}
	public void setResponseFromUserEntity(UserEntity user) {
		this.setUsername(user.getUsername());
		this.setPhone(user.getPhone());
		this.setRoles(user.getRoles());
	}
}
