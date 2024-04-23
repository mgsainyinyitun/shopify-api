package com.shopify.api.repository;

import com.shopify.api.models.trade.TradeHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeHistoryRepository extends JpaRepository<TradeHistoryEntity,Long> {
}
