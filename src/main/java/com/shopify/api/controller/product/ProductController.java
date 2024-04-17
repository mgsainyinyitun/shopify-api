package com.shopify.api.controller.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

//	 private List<Product> products = new ArrayList<>();
//	
//	 @GetMapping
//	    public List<Product> getAllProducts() {
//		 
//		 	products.clear();
//		 	
//		 	products.add(new Product(1,"Product 1",24.5));
//			products.add(new Product(2,"Product 2",24.5));
//		 
//	        return products;
//	    }
}
