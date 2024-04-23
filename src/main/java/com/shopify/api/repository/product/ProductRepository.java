package com.shopify.api.repository.product;

import com.shopify.api.models.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    ProductEntity findByUid(String uid);

    List<ProductEntity> findAllByMerchantId(Long id);

    ProductEntity findFirstByMerchantId(Long id);

    @Query( "SELECT p FROM ProductEntity p  WHERE p.id NOT IN (SELECT t.product.id FROM TradeHistoryEntity t WHERE t.user.id = :userId AND t.state='FINISHED')")
    List<ProductEntity> findUnContractedProduct(@Param("userId") Long userId);

}
