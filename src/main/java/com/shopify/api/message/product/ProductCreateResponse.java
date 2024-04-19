package com.shopify.api.message.product;

import com.shopify.api.models.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateResponse {
    private String name;
    private String description;
    private Double commission;
    private Long merchantId;
    private Long id;

    public ProductCreateResponse(ProductEntity product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.commission = product.getCommission();
        this.merchantId = product.getMerchant().getId();
    }
}
