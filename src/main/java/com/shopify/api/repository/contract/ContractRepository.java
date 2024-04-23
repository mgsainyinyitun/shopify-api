package com.shopify.api.repository.contract;

import com.shopify.api.models.contract.ContractEntity;
import com.shopify.api.models.merchant.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    List<ContractEntity> findAllByUserId(Long userId);

    @Query("SELECT c FROM ContractEntity  c WHERE c.user.id=:userId AND c.status = 'approved'")
    ContractEntity findByStatusIsApproveForUser(@Param("userId") Long id);

    @Query("SELECT c FROM ContractEntity c WHERE c.merchant = :merchantId AND c.status = 'approved' AND c.taskComplete = false")
    ContractEntity findCurrentContractByMerchant(@Param("merchantId") MerchantEntity merchantId);

    @Query("SELECT c FROM ContractEntity c WHERE c.status = 'approved' AND c.taskComplete = false")
    ContractEntity findCurrentContract();

}
