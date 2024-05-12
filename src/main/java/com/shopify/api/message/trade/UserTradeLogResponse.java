package com.shopify.api.message.trade;

import com.shopify.api.constant.TRADING_STATE;
import com.shopify.api.models.trade.TradeHistoryEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserTradeLogResponse {

    String orderId;
    String merchant;
    Integer taskNumber;
    Double orderPrice;
    Double commission;
    LocalDateTime time;
    TRADING_STATE state;

    public  UserTradeLogResponse(TradeHistoryEntity trade){
        this.orderId = trade.getOrderId();
        this.merchant =trade.getProduct().getMerchant().getName();
        this.taskNumber = trade.getTaskNumber();
        this.orderPrice = trade.getOrderPrice();
        this.commission = trade.getProduct().getCommission();
        this.time = trade.getTradedAt();
        this.state = trade.getState();
    }
}
