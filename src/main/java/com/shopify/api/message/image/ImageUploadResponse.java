package com.shopify.api.message.image;

import com.shopify.api.models.image.ImageEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageUploadResponse {
    private Long id;
    private String name;
    private String type;
}
