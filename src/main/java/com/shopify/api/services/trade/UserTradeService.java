package com.shopify.api.services.trade;

import com.shopify.api.message.trade.*;
import com.shopify.api.models.user.UserEntity;

import java.util.List;

public interface UserTradeService {
    UserTradeResponse trade(UserTradeRequest request, UserEntity user);

    UserTradeFinishedResponse tradeFinished(UserTradeFinishedRequest request, UserEntity user);

    UserTradeLogListResponse tradeLogs(UserTradeLogRequest request, UserEntity user);
}
