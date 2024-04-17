package com.shopify.api.dto.user;

import com.shopify.api.models.user.UserEntity;

public class UserDTO {
	private String username;
	private String phone;
	private int membership;
	private String country;
	private String language;
	private Double balance;
	private Double revenue = 0.0;
	private String uid;
	
	private String error;
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public UserDTO(UserEntity user) {
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.membership = user.getMembership();
        this.country = user.getCountry();
        this.language = user.getLanguage();
        this.balance = user.getBalance();
        this.revenue = user.getRevenue();
        this.uid = user.getUid();
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
	public int getMembership() {
		return membership;
	}
	public void setMembership(int membership) {
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
}
