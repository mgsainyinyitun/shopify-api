package com.shopify.api.message.merchant;

public class MerchantListRequest {
	private String name;
	private Integer rating;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
