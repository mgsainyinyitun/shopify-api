package com.shopify.api.message.trade;


import com.shopify.api.constant.TRADING_STATE;
import com.shopify.api.models.trade.TradeHistoryEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserTradeFinishedResponse {
    private Long productId;

    private String productName;

    private Integer taskNumber;

    private Double orderPrice;

    private TRADING_STATE state;

    private LocalDateTime tradedAt;

    public UserTradeFinishedResponse(TradeHistoryEntity trade) {
        this.setProductId(trade.getProduct().getId());
        this.setProductName(trade.getProduct().getName());
        this.setTaskNumber(trade.getTaskNumber());
        this.setOrderPrice(trade.getOrderPrice());
        this.setState(trade.getState());
        this.setTradedAt(trade.getTradedAt());
    }
}
