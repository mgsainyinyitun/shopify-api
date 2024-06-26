package com.shopify.api.message.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductListResponse {
    List<ProductResponse> products;
}
