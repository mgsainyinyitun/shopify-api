package com.shopify.api.message.merchant;

import com.shopify.api.models.merchant.MerchantEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}

