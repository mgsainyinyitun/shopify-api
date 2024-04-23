package com.shopify.api.message.image;

import com.shopify.api.constant.IMAGE_TYPE;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageUploadRequest {
    IMAGE_TYPE type;
    String uid;
}
