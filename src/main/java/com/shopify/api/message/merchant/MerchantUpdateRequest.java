package com.shopify.api.message.merchant;

import com.shopify.api.models.merchant.MerchantEntity;

public class MerchantUpdateRequest {
	private Long id;
	
	private String name;
	
	private String description;
	
	
	private Integer rating;

	public MerchantEntity update(MerchantEntity merchant) {
		if(this.id != null)merchant.setId(id);
		if(this.name!=null)merchant.setName(this.name);
		if(this.description!=null)merchant.setDescription(this.description);
		if(this.rating!=null)merchant.setRating(this.rating);
	
		return merchant;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
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

