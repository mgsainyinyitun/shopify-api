package com.shopify.api.services.impl.image;

import com.shopify.api.constant.IMAGE_TYPE;
import com.shopify.api.message.image.ImageRequest;
import com.shopify.api.message.image.ImageUploadRequest;
import com.shopify.api.message.image.ImageUploadResponse;
import com.shopify.api.models.bank.BankInfoEntity;
import com.shopify.api.models.image.ImageEntity;
import com.shopify.api.models.merchant.MerchantEntity;
import com.shopify.api.models.product.ProductEntity;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.repository.bank.BankInfoRepository;
import com.shopify.api.repository.image.ImageRepository;
import com.shopify.api.repository.merchant.MerchantRepository;
import com.shopify.api.repository.product.ProductRepository;
import com.shopify.api.repository.user.UserRepository;
import com.shopify.api.services.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Autowired
    private ProductRepository productRepository;

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
        image.setUuid(request.getUid());

        imageRepository.save(image);
        res.setId(image.getId());
        res.setType(image.getType());
        res.setName(image.getName());

        switch (request.getType()){
            case USER:
                UserEntity user = userRepository.findByUid(request.getUid());
                user.setImage(image);
                userRepository.save(user);
                break;
            case MERCHANT:
                MerchantEntity merchant = merchantRepository.findByUid(request.getUid());
                merchant.setImage(image);
                merchantRepository.save(merchant);
                break;
            case PRODUCT:
                ProductEntity product = productRepository.findByUid(request.getUid());
                product.setImage(image);
                productRepository.save(product);
                break;
            case BANK:
                BankInfoEntity bankInfo = bankInfoRepository.findByUid(request.getUid());
                bankInfo.setImage(image);
                bankInfoRepository.save(bankInfo);
                break;
        }

        return res;
    }

    @Override
    public byte[] getImage(ImageRequest request) {
        ImageEntity dbImage = imageRepository.findByUuid(request.getUuid()).get();
        return dbImage.getImageData();
    }
}
