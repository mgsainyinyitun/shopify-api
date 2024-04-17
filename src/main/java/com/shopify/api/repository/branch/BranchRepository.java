package com.shopify.api.repository.branch;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopify.api.models.branch.BranchEntity;

public interface BranchRepository extends JpaRepository<BranchEntity, Long>{
	Optional<BranchEntity> findById(Long id);
	
	Optional<BranchEntity> findByCode(String code);
}
