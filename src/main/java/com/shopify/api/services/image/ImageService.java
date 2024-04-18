package com.shopify.api.services.image;

import com.shopify.api.message.image.ImageRequest;
import com.shopify.api.message.image.ImageUploadRequest;
import com.shopify.api.message.image.ImageUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageUploadResponse uploadImage(MultipartFile file, ImageUploadRequest request) throws IOException;

    byte[] getImage(ImageRequest request) ;

}
