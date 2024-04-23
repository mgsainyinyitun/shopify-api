package com.shopify.api.message.product;

import com.shopify.api.models.product.ProductEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private  Long id;
    private  String name;
    private  String description;
    private  Double commission;
    private  Long merchantId;
    private String uid;

    public ProductResponse(ProductEntity product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.commission = product.getCommission();
        this.merchantId = product.getMerchant().getId();
        this.uid = product.getUid();
    }
}
