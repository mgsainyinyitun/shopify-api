package com.shopify.api.message.admin.user;

public class AdminUserDeleteRequest {
	private Long id;
	
	private String uid;

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
}
