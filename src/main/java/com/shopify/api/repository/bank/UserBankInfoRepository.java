package com.shopify.api.repository.bank;

import com.shopify.api.models.bank.user.UserBankInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBankInfoRepository extends JpaRepository<UserBankInfoEntity,Long> {

    List<UserBankInfoEntity> findAllByUserId(Long userId);

}
