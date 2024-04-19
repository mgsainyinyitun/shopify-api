package com.shopify.api.controller.product;

import java.util.ArrayList;
import java.util.List;

import com.shopify.api.message.ErrorMessageResponse;
import com.shopify.api.message.merchant.*;
import com.shopify.api.message.product.*;
import com.shopify.api.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductCreateRequest request) {
        ProductCreateResponse response;
        try {
            response = productService.create(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PostMapping("/all")
    public ResponseEntity<?> listAll(@RequestBody ProductListRequest request) {
        ProductListResponse response = new ProductListResponse();
        try {
            List<ProductResponse> products = productService.getAllProducts(request);
            response.setProducts(products);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProductUpdateRequest request) {
        ProductUpdateResponse response ;
        try {
            response = productService.update(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@ModelAttribute ProductDeleteRequest request) {
        ProductDeleteResponse response ;
        try {
            response = productService.delete(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

}
