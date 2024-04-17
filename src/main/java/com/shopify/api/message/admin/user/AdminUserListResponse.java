package com.shopify.api.message.admin.user;

import java.util.ArrayList;
import java.util.List;

import com.shopify.api.models.user.UserEntity;

public class AdminUserListResponse {
	private Long id;
	private String username;
	private String phone;
	private Integer membership;
	private String country;
	private String language;
	private Double balance;
	private Double revenue;
	private String uid;
	private Boolean freeze;
	private String Error;

	public static List<AdminUserListResponse> UserEntityToUserListResponse(List<UserEntity> users) {
		List<AdminUserListResponse> list = new ArrayList<AdminUserListResponse>();
		for (UserEntity user : users) {
			list.add(convert(user));
		}
		return list;
	}

	private static AdminUserListResponse convert(UserEntity user) {
		AdminUserListResponse res = new AdminUserListResponse();
		res.setId(user.getId());
		res.setUsername(user.getUsername());
		res.setPhone(user.getPhone());
		res.setMembership(user.getMembership());
		res.setCountry(user.getCountry());
		res.setLanguage(user.getLanguage());
		res.setBalance(user.getBalance());
		res.setRevenue(user.getRevenue());
		res.setUid(user.getUid());
		res.setFreeze(user.isFreeze());
		return res;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Integer getMembership() {
		return membership;
	}

	public void setMembership(Integer membership) {
		this.membership = membership;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Boolean getFreeze() {
		return freeze;
	}

	public void setFreeze(Boolean freeze) {
		this.freeze = freeze;
	}

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}
}
