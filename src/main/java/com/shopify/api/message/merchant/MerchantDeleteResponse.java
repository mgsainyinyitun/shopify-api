package com.shopify.api.message.merchant;

public class MerchantDeleteResponse {
	private Long id;
	
	private String Error;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}
}
