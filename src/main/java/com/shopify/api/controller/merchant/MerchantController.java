package com.shopify.api.controller.merchant;

import com.shopify.api.constant.TYPE;
import com.shopify.api.message.ErrorMessageResponse;
import com.shopify.api.message.image.ImageRequest;
import com.shopify.api.message.image.ImageUploadRequest;
import com.shopify.api.message.image.ImageUploadResponse;
import com.shopify.api.services.image.ImageService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/merchant")
@CrossOrigin(origins = "http://localhost:3000")
public class MerchantController {
	@Autowired
	MerchantService merchantService;

	@Autowired
	ImageService imageService;

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody MerchantCreateRequest merchantCreateRequest) {
		MerchantCreateResponse res;
		try {
			res = merchantService.create(merchantCreateRequest);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> listAll(@RequestBody MerchantListRequest request) {
		MerchantListResponse res;
		try {
			res = merchantService.list(request);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody MerchantUpdateRequest request) {
		MerchantUpdateResponse res ;
		try {
			res = merchantService.update(request);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody MerchantDeleteRequest request) {
		MerchantDeleteResponse res ;
		try {
			res = merchantService.delete(request);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
	}

	@GetMapping("/image/get") ResponseEntity<?> getImage(@RequestBody ImageRequest request){
		try{
			byte[] image = imageService.getImage(request);
			return  ResponseEntity.ok(image);
		}catch (Exception e) {
			ErrorMessageResponse err = new ErrorMessageResponse("ERR", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
	}

	@PostMapping("/image/upload")
	public ResponseEntity<?> uploadImage(
			@RequestParam("image") MultipartFile file,
			@RequestParam("type") String type,
			@RequestParam("ownerId") String ownerId
	) throws IOException {
		ImageUploadResponse res;
		ImageUploadRequest req = new ImageUploadRequest();
		try {
			req.setType(TYPE.fromString(type));
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
