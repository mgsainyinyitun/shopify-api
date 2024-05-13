package com.shopify.api.controller.admin.user;

import java.util.ArrayList;
import java.util.List;

import com.shopify.api.message.admin.user.*;
import com.shopify.api.message.error.ErrorMessageResponse;
import com.shopify.api.services.contract.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.shopify.api.services.admin.user.AdminUserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins="*")
public class AdminUserController {
	
	@Autowired
	AdminUserService adminUserService;

	@Autowired
	ContractService contractService;

	@GetMapping("/trade-user/all")
	public ResponseEntity<?> allTradeUser(@ModelAttribute AdminUserListRequest request){
		AdminUsersListResponse res = new AdminUsersListResponse();
		try {
			res = adminUserService.list(request);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
	}
	
	@GetMapping("/trade-user/detail")
	public ResponseEntity<?> tradeUserDetail(@ModelAttribute AdminTradeUserRequest request){
		AdminTradeUserResponse res;
		try {
			res = adminUserService.detail(request);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
	}

	@GetMapping("/trade-user/contracts")
	public ResponseEntity<?> userContracts(@ModelAttribute AdminUserContractHistListRequest request){
		AdminUserContractHistListResponse res;
		try {
			res = contractService.getContractsList(request);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
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

	@PostMapping("/user/balance/increase")
	public ResponseEntity<?> increaseBalance(@RequestBody AdminUserBalanceIncreaseRequest request) {
		try {
			AdminUserBalanceIncreaseResponse
			res = adminUserService.increaseBalance(request);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
	}

	@PostMapping("/user/membership/change")
	public ResponseEntity<?> membershipChange(@RequestBody AdminUserMembershipChangeRequest request) {
		try {
			AdminUserMembershipChangeResponse
					res = adminUserService.membershipChange(request);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
	}
}
