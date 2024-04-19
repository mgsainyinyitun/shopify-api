package com.shopify.api.repository.product;

import com.shopify.api.models.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> findAllByMerchantId(Long id);

}
