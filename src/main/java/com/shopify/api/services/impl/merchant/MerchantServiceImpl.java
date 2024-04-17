package com.shopify.api.services.impl.merchant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopify.api.message.merchant.MerchantCreateRequest;
import com.shopify.api.message.merchant.MerchantCreateResponse;
import com.shopify.api.message.merchant.MerchantDeleteRequest;
import com.shopify.api.message.merchant.MerchantDeleteResponse;
import com.shopify.api.message.merchant.MerchantListRequest;
import com.shopify.api.message.merchant.MerchantListResponse;
import com.shopify.api.message.merchant.MerchantResponse;
import com.shopify.api.message.merchant.MerchantUpdateRequest;
import com.shopify.api.message.merchant.MerchantUpdateResponse;
import com.shopify.api.models.merchant.MerchantEntity;
import com.shopify.api.repository.merchant.MerchantRepository;
import com.shopify.api.services.merchant.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {

	@Autowired
	MerchantRepository merchantRepository;

	@Override
	public MerchantCreateResponse create(MerchantCreateRequest merchantCreateRequest) {
		MerchantEntity merchant = merchantCreateRequest.toMerchantEntity();
		merchantRepository.save(merchant);
		return new MerchantCreateResponse(merchant);
	}

	@Override
	public MerchantListResponse list(MerchantListRequest merchantListRequest) {
		MerchantListResponse res = new MerchantListResponse();
		List<MerchantResponse> merchants = new ArrayList<MerchantResponse>();

		if (merchantListRequest.getName() != null) {
			MerchantEntity merchant = merchantRepository.findByName(merchantListRequest.getName()).get();
			merchants.add(new MerchantResponse(merchant));
		}

		if (merchantListRequest.getRating() != null) {
			List<MerchantEntity> mrs = merchantRepository.findByRating(merchantListRequest.getRating());
			for (MerchantEntity m : mrs) {
				merchants.add(new MerchantResponse(m));
			}
		}
		if (merchantListRequest.getRating() == null && merchantListRequest.getName() == null) {
			List<MerchantEntity> mrs = merchantRepository.findAll();
			for (MerchantEntity m : mrs) {
				merchants.add(new MerchantResponse(m));
			}
		}
		res.setMerchants(merchants);
		return res;
	}

	@Override
	public MerchantUpdateResponse update(MerchantUpdateRequest merchantUpdateRequest) {
		MerchantUpdateResponse res = new MerchantUpdateResponse();
		MerchantEntity merchant = merchantRepository.findById(merchantUpdateRequest.getId()).get();
		merchant = merchantUpdateRequest.update(merchant);
		merchantRepository.save(merchant);
		res.setName(merchant.getName());
		return res;
	}

	@Override
	public MerchantDeleteResponse delete(MerchantDeleteRequest request) {
		MerchantDeleteResponse res = new MerchantDeleteResponse();
		
		MerchantEntity merchant;
		merchant = merchantRepository.findById(request.getId()).get();
		merchantRepository.delete(merchant);
		res.setId(merchant.getId());
		return res;
	}
}
