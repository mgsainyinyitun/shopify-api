package com.shopify.api.message.product;

import com.shopify.api.models.product.ProductEntity;
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

    public TradeProductResponse(ProductEntity product,Long tradeId){
        this.tradeId = tradeId;
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.commission = product.getCommission();
        this.uid = product.getUid();
        this.rating = product.getRating();
    }
}
