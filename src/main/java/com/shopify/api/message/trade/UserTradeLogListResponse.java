package com.shopify.api.message.trade;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserTradeLogListResponse {
    List<UserTradeLogResponse> tradeLogs;
}
