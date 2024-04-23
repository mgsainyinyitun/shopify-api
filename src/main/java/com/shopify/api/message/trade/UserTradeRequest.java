package com.shopify.api.message.trade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTradeRequest {
    private Long productId;

    private Integer taskNumber;

}
