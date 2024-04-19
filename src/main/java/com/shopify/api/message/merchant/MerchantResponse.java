package com.shopify.api.message.merchant;

import com.shopify.api.models.merchant.MerchantEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantResponse {
	private Long id;
	private String name;
	private Integer rating;
	private String description;
	private Double lowerLimit;
	private String imageUuid;
	
	public MerchantResponse(MerchantEntity merchant) {
		this.id = merchant.getId();
		this.name = merchant.getName();
		this.rating = merchant.getRating();
		this.description = merchant.getDescription();
		this.lowerLimit = merchant.getLowerLimit();
		this.imageUuid = merchant.getImage().getUuid();
	}

}
