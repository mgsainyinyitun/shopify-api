package com.shopify.api.services.impl.product;

import com.shopify.api.message.product.*;
import com.shopify.api.models.merchant.MerchantEntity;
import com.shopify.api.models.product.ProductEntity;
import com.shopify.api.models.trade.TradeHistoryEntity;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.repository.TradeHistoryRepository;
import com.shopify.api.repository.merchant.MerchantRepository;
import com.shopify.api.repository.product.ProductRepository;
import com.shopify.api.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    private TradeHistoryRepository tradeHistoryRepository;

    @Override
    public ProductCreateResponse create(ProductCreateRequest request) {
        ProductEntity product = request.toProductEntity();
        product.setMerchant(merchantRepository.findById(request.getMerchantId()).get());
        productRepository.save(product);
        return new ProductCreateResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts(ProductListRequest request) {
        List<ProductResponse> responses = new ArrayList<>();
        List<ProductEntity> products;
        if(request.getMerchantId()!=null){
            products = productRepository.findAllByMerchantId(request.getMerchantId());
        }else{
            products = productRepository.findAll();
        }
        for(ProductEntity product:products){
            responses.add(new ProductResponse(product));
        }
        return responses;
    }

    @Override
    public ProductUpdateResponse update(ProductUpdateRequest request) {
        ProductUpdateResponse response = new ProductUpdateResponse();

        ProductEntity product = productRepository.findById(request.getId()).get();
        ProductEntity updatedProduct = request.update(product);

        if(request.getMerchantId()!=null){
            MerchantEntity merchant = merchantRepository.findById(request.getMerchantId()).get();
            updatedProduct.setMerchant(merchant);
            response.setMerchantId(merchant.getId());
        }

        response.fromProductEntity(product);
        productRepository.save(updatedProduct);
        return response;
    }

    @Override
    public ProductDeleteResponse delete(ProductDeleteRequest request) {
        ProductEntity product = productRepository.findById(request.getId()).get();
        productRepository.delete(product);
        ProductDeleteResponse response = new ProductDeleteResponse();
        response.setId(product.getId());
        return response;
    }

    @Override
    public TradeProductResponse tradeProduct(TradeProductRequest request, UserEntity user) {
        List<TradeHistoryEntity> dbTrades = tradeHistoryRepository.findCurrentTrades(user.getId());
        if(dbTrades.isEmpty()){
            throw  new EmptyResultDataAccessException(0);
        }
        ProductEntity product = productRepository.findById(dbTrades.get(0).getProduct().getId()).get();
        return new TradeProductResponse(product,dbTrades.get(0));
    }
}
