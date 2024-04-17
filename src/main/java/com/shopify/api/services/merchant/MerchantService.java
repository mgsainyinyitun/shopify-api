package com.shopify.api.services.merchant;

import com.shopify.api.message.merchant.MerchantCreateRequest;
import com.shopify.api.message.merchant.MerchantCreateResponse;
import com.shopify.api.message.merchant.MerchantDeleteRequest;
import com.shopify.api.message.merchant.MerchantDeleteResponse;
import com.shopify.api.message.merchant.MerchantListRequest;
import com.shopify.api.message.merchant.MerchantListResponse;
import com.shopify.api.message.merchant.MerchantUpdateRequest;
import com.shopify.api.message.merchant.MerchantUpdateResponse;

public interface MerchantService {
	
	MerchantCreateResponse create(MerchantCreateRequest merchantCreateRequest);
	
	MerchantListResponse list(MerchantListRequest merchantListRequest);
	
	MerchantUpdateResponse update(MerchantUpdateRequest merchantUpdateRequest);
	
	MerchantDeleteResponse delete(MerchantDeleteRequest merchantDeleteRequest);
	
}
