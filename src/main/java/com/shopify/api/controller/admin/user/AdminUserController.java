package com.shopify.api.controller.admin.user;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.api.message.admin.user.AdminUserCreateRequest;
import com.shopify.api.message.admin.user.AdminUserCreateResponse;
import com.shopify.api.message.admin.user.AdminUserDeleteReponse;
import com.shopify.api.message.admin.user.AdminUserDeleteRequest;
import com.shopify.api.message.admin.user.AdminUserListRequest;
import com.shopify.api.message.admin.user.AdminUserListResponse;
import com.shopify.api.message.admin.user.AdminUserUpdateRequest;
import com.shopify.api.message.admin.user.AdminUserUpdateResponse;
import com.shopify.api.services.admin.user.AdminUserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins="*")
public class AdminUserController {
	
	@Autowired
	AdminUserService adminUserService;

	@GetMapping("/user/all")
	public ResponseEntity<?> all(){
		return null;
	}
	
	
	@PostMapping("/user/create")
	public ResponseEntity<AdminUserCreateResponse> create(@RequestBody AdminUserCreateRequest adminUserCreateRequest) {
		AdminUserCreateResponse res = new AdminUserCreateResponse();
		try {
			res = adminUserService.create(adminUserCreateRequest);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			res.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
	}

	@GetMapping("/user/all")
	public ResponseEntity<List<AdminUserListResponse>> all(@RequestBody AdminUserListRequest adminUserListRequest) {
		List<AdminUserListResponse> userList = new ArrayList<AdminUserListResponse>();
		try {
			userList = adminUserService.listAll(adminUserListRequest);
			return ResponseEntity.ok(userList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@PutMapping("/user/update")
	public ResponseEntity<AdminUserUpdateResponse> update(@RequestBody AdminUserUpdateRequest updateUserRequest) {
		AdminUserUpdateResponse res = new AdminUserUpdateResponse();
		try {
			res = adminUserService.update(updateUserRequest);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			res.setError("Login user not Found!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
	}

	@DeleteMapping("/user/delete")
	@Transactional
	public ResponseEntity<AdminUserDeleteReponse> delete(@RequestBody AdminUserDeleteRequest userDeleteRequest) {
		AdminUserDeleteReponse res = new AdminUserDeleteReponse();
		try {
			res = adminUserService.deleteUser(userDeleteRequest);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			res.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
	}
}
