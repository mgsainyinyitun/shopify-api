package com.shopify.api.services.product;

import com.shopify.api.message.product.*;
import com.shopify.api.models.user.UserEntity;

import java.util.List;

public interface ProductService {
    ProductCreateResponse create(ProductCreateRequest request);
    List<ProductResponse> getAllProducts(ProductListRequest request);
    ProductUpdateResponse update(ProductUpdateRequest request);
    ProductDeleteResponse delete(ProductDeleteRequest request);

    TradeProductResponse tradeProduct(TradeProductRequest request, UserEntity user);
}
