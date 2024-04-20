package com.shopify.api.message.image;

import com.shopify.api.constant.IMAGE_TYPE;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageUploadRequest {
    IMAGE_TYPE type;

    String ownerId;

    String uuid;

    public String generateUUID() {
        String id = String.format("%1$15s", this.ownerId).replace(' ', '0');
        return type.name() + id;
    }
}
