package com.shopify.api.repository;

import com.shopify.api.models.product.ProductEntity;
import com.shopify.api.models.trade.TradeHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TradeHistoryRepository extends JpaRepository<TradeHistoryEntity,Long> {
    List<TradeHistoryEntity> findAllByUserId(Long userId);

    @Query("select t from TradeHistoryEntity  t where t.state = 'NOT_START' OR t.state='PENDING' AND t.user.id = :userId AND t.product.id=:productId")
    List<TradeHistoryEntity> findCurrentTrades(@Param("userId") Long userId, @Param("productId") Long productId);

}
