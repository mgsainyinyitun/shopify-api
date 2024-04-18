package com.shopify.api.repository.image;

import com.shopify.api.models.image.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    Optional<ImageEntity> findByUuid(String uuid);
}
