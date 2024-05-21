package com.shopify.api.message.merchant;

import com.shopify.api.models.merchant.MerchantEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantCreateResponse {

	private Long id;
	private String name;
	private String description;
	private Integer rating;
	private String uid;
	private String Error;
	
	public MerchantCreateResponse() {
	}

	public MerchantCreateResponse(MerchantEntity merchant) {
		this.id = merchant.getId();
		this.name = merchant.getName();
		this.description = merchant.getDescription();
		this.rating = merchant.getRating();
		this.uid = merchant.getUid();
	}

	public MerchantCreateResponse(Long id, String name, String description, Integer rating) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.rating = rating;
	}
}
