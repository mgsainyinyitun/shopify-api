package com.shopify.api.message.merchant;

public class MerchantUpdateResponse {
	private String name;

	private String Error;

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
