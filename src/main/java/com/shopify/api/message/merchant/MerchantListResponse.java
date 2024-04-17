package com.shopify.api.message.merchant;

import java.util.List;

public class MerchantListResponse {

	private List<MerchantResponse> merchants;
	
	private String Error;

	public List<MerchantResponse> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<MerchantResponse> merchants) {
		this.merchants = merchants;
	}

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}
}
