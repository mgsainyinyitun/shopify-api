package com.shopify.api.repository.contract;

import com.shopify.api.models.contract.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    List<ContractEntity> findAllByUserId(Long userId);
}
