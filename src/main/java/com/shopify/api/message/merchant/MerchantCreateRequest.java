package com.shopify.api.message.merchant;

import com.shopify.api.models.merchant.MerchantEntity;

public class MerchantCreateRequest {

	private String name;
	private String description;

	private Integer rating;

	public String getName() {
		return name;
	}
	
	public MerchantEntity toMerchantEntity() {
		MerchantEntity merchant = new MerchantEntity();
		merchant.setName(this.name);
		merchant.setDescription(this.description);
		merchant.setRating(this.rating);
		return merchant;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
