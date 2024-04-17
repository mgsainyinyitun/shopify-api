package com.shopify.api.message.admin.user;

public class AdminUserDeleteReponse {
	private Long id;
	private String uid;
	private String Error;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getError() {
		return Error;
	}
	public void setError(String error) {
		Error = error;
	}
}
