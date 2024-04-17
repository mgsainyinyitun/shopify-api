package com.shopify.api.message.merchant;

import com.shopify.api.models.merchant.MerchantEntity;

public class MerchantResponse {
	private Long id;
	private String name;
	private Integer rating;
	private String description;
	
	
	public MerchantResponse(MerchantEntity merchant) {
		this.id = merchant.getId();
		this.name = merchant.getName();
		this.rating = merchant.getRating();
		this.description = merchant.getDescription();
	}
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
