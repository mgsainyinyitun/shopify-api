package com.shopify.api.controller.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.api.message.merchant.MerchantCreateRequest;
import com.shopify.api.message.merchant.MerchantCreateResponse;
import com.shopify.api.message.merchant.MerchantDeleteRequest;
import com.shopify.api.message.merchant.MerchantDeleteResponse;
import com.shopify.api.message.merchant.MerchantListRequest;
import com.shopify.api.message.merchant.MerchantListResponse;
import com.shopify.api.message.merchant.MerchantUpdateRequest;
import com.shopify.api.message.merchant.MerchantUpdateResponse;
import com.shopify.api.services.merchant.MerchantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/merchant")
@CrossOrigin(origins = "http://localhost:3000")
public class MerchantController {
	@Autowired
	MerchantService merchantService;

	@PostMapping("/create")
	public ResponseEntity<MerchantCreateResponse> create(@RequestBody MerchantCreateRequest merchantCreateRequest) {
		MerchantCreateResponse res = new MerchantCreateResponse();
		try {
			res = merchantService.create(merchantCreateRequest);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			res.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<MerchantListResponse> listAll(@RequestBody MerchantListRequest request) {
		MerchantListResponse res = new MerchantListResponse();
		try {
			res = merchantService.list(request);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			res.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<MerchantUpdateResponse> update(@RequestBody MerchantUpdateRequest request) {
		MerchantUpdateResponse res = new MerchantUpdateResponse();
		try {
			res = merchantService.update(request);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			res.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<MerchantDeleteResponse> delete(@RequestBody MerchantDeleteRequest request){
		MerchantDeleteResponse res = new MerchantDeleteResponse();
		try {
			res = merchantService.delete(request);
			return ResponseEntity.ok(res);
		}catch(Exception e) {
			res.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
	}

}
