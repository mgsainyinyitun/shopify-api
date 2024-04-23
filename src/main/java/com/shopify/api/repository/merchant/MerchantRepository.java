package com.shopify.api.repository.merchant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopify.api.models.merchant.MerchantEntity;

public interface MerchantRepository extends JpaRepository<MerchantEntity,Long>{
	Optional<MerchantEntity> findByName(String name);

	MerchantEntity findByUid(String uuid);

	List<MerchantEntity> findByRating(Integer rating);
	
}
