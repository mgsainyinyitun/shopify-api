package com.shopify.api.message.product;

import com.shopify.api.models.product.ProductEntity;
import com.shopify.api.models.trade.TradeHistoryEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeProductResponse {
    private Long tradeId;

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Double commission;

    private String uid;

    private Integer rating;

    public TradeProductResponse(ProductEntity product,TradeHistoryEntity trade){
        this.tradeId = trade.getId();
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = trade.getOrderPrice();
        this.commission = product.getCommission();
        this.uid = product.getUid();
        this.rating = product.getRating();
    }
}
