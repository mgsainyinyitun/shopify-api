package com.shopify.api.message.product;

import com.shopify.api.models.product.ProductEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private Double commission;
    private Long merchantId;

    public ProductEntity update(ProductEntity product) {
        if(this.name!=null)product.setName(this.name);
        if(this.description!=null)product.setDescription(this.description);
        if(this.commission!=null)product.setCommission(this.commission);
        return  product;
    }

}
