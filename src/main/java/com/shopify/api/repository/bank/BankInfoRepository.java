package com.shopify.api.repository.bank;

import com.shopify.api.models.bank.BankInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankInfoRepository extends JpaRepository<BankInfoEntity,Long> {
    BankInfoEntity findByUid(String uid);
}
