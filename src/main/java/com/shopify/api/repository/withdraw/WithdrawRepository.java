package com.shopify.api.repository.withdraw;

import com.shopify.api.models.user.UserEntity;
import com.shopify.api.models.withdraw.WithdrawEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawRepository extends JpaRepository<WithdrawEntity,Long> {
    List<WithdrawEntity> findAllByUser(UserEntity user);
}
