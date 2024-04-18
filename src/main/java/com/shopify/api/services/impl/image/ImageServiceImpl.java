package com.shopify.api.services.impl.image;

import com.shopify.api.message.image.ImageRequest;
import com.shopify.api.message.image.ImageUploadRequest;
import com.shopify.api.message.image.ImageUploadResponse;
import com.shopify.api.models.image.ImageEntity;
import com.shopify.api.repository.image.ImageRepository;
import com.shopify.api.services.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public ImageUploadResponse uploadImage(MultipartFile file, ImageUploadRequest request) throws  IOException {
        ImageUploadResponse res = new ImageUploadResponse();
//        ImageEntity img = imageRepository.save(
//                ImageEntity.builder()
//                        .name(file.getOriginalFilename())
//                        .type(file.getContentType())
//                        .imageData(file.getBytes())
//                        .build()
//        );

        ImageEntity image = new ImageEntity();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImageData(file.getBytes());
        image.setUuid(request.getUuid());

        imageRepository.save(image);
        res.setId(image.getId());
        res.setType(image.getType());
        res.setName(image.getName());
        return res;
    }

    @Override
    public byte[] getImage(ImageRequest request) {
        ImageEntity dbImage = imageRepository.findByUuid(request.getUuid()).get();
        return dbImage.getImageData();
    }
}
