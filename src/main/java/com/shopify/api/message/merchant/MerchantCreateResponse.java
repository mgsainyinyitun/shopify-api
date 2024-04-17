package com.shopify.api.message.merchant;

import com.shopify.api.models.merchant.MerchantEntity;

public class MerchantCreateResponse {

	private Long id;
	private String name;
	private String description;
	private Integer rating;
	private String Error;
	
	public MerchantCreateResponse() {
	}

	public MerchantCreateResponse(MerchantEntity merchant) {
		this.id = merchant.getId();
		this.name = merchant.getName();
		this.description = merchant.getDescription();
		this.rating = merchant.getRating();
	}

	public MerchantCreateResponse(Long id, String name, String description, Integer rating) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.rating = rating;
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

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}
}
