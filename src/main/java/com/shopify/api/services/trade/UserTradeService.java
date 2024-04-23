package com.shopify.api.services.trade;

import com.shopify.api.message.trade.UserTradeFinishedRequest;
import com.shopify.api.message.trade.UserTradeFinishedResponse;
import com.shopify.api.message.trade.UserTradeRequest;
import com.shopify.api.message.trade.UserTradeResponse;
import com.shopify.api.models.user.UserEntity;

public interface UserTradeService {
    UserTradeResponse trade(UserTradeRequest request, UserEntity user);

    UserTradeFinishedResponse tradeFinished(UserTradeFinishedRequest request, UserEntity user);
}
