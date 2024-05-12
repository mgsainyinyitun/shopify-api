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
public class ProductCreateRequest {
    private String name;
    private String description;
    private Double commission;
    private Long merchantId;
    private Double price;

    public ProductEntity toProductEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(name);
        productEntity.setDescription(description);
        productEntity.setCommission(commission);
        productEntity.setPrice(price);
        return productEntity;
    }
}
