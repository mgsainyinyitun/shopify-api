package com.shopify.api.message.product;

import com.shopify.api.models.product.ProductEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductUpdateResponse {
    private Long id;
    private String name;
    private String description;
    private Double commission;
    private Long merchantId;
    private String uid;

    public void fromProductEntity(ProductEntity product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.commission = product.getCommission();
        this.uid = product.getUid();
    }
}
