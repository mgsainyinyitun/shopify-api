package com.shopify.api.controller.image;


import com.shopify.api.constant.IMAGE_TYPE;
import com.shopify.api.message.error.ErrorMessageResponse;
import com.shopify.api.message.image.ImageRequest;
import com.shopify.api.message.image.ImageUploadRequest;
import com.shopify.api.message.image.ImageUploadResponse;
import com.shopify.api.services.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "http://localhost:3000")

public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/get")
    ResponseEntity<?> getImage(@RequestBody ImageRequest request){
        try{
            byte[] image = imageService.getImage(request);
            return  ResponseEntity.ok(image);
        }catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERR", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("type") String type,
            @RequestParam("ownerId") String ownerId
    ) throws IOException {
        ImageUploadResponse res;
        ImageUploadRequest req = new ImageUploadRequest();
        try {
            req.setType(IMAGE_TYPE.fromString(type));
            req.setOwnerId(ownerId);
            req.setUuid(req.generateUUID());
            res = imageService.uploadImage(file,req);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            ErrorMessageResponse errRes = new ErrorMessageResponse(
                    "ERR:",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);
        }
    }
}
