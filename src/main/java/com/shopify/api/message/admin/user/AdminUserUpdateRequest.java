package com.shopify.api.message.admin.user;
import com.shopify.api.models.user.UserEntity;

public class AdminUserUpdateRequest {
	
	private Long id;
	private String username;
	private String phone;
	private Integer membership;
	private String country;
	private String language;
	private Double balance;
	private Double revenue = 0.0;
	private String uid;
	private Boolean freeze;
	
	public UserEntity update(UserEntity user) {
		if(this.phone != null)user.setPhone(this.phone);
		if(this.membership!=null)user.setMembership(this.membership);
		if(this.country!=null)user.setCountry(this.country);
		if(this.language!=null)user.setLanguage(this.language);
		if(this.balance!=null)user.setBalance(this.balance);
		if(this.revenue!=null)user.setRevenue(this.revenue);
		if(this.freeze != null)user.setFreeze(this.freeze);
		return user;
	}
	
	public boolean isFreeze() {
		return freeze;
	}

	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getFreeze() {
		return freeze;
	}

	public void setFreeze(Boolean freeze) {
		this.freeze = freeze;
	}

	public void setMembership(Integer membership) {
		this.membership = membership;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
