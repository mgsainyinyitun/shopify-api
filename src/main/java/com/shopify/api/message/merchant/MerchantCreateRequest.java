package com.shopify.api.message.merchant;

import com.shopify.api.models.merchant.MerchantEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantCreateRequest {

	private String name;
	private String description;
	private Integer rating;
	private  Double lowerLimit;
	
	public MerchantEntity toMerchantEntity() {
		MerchantEntity merchant = new MerchantEntity();
		merchant.setName(this.name);
		merchant.setDescription(this.description);
		merchant.setRating(this.rating);
		merchant.setLowerLimit(this.lowerLimit);
		return merchant;
	}
}
