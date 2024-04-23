package com.shopify.api.services.impl.admin.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopify.api.exceptions.UsernameAlreadyTakenException;
import com.shopify.api.message.admin.user.AdminUserCreateRequest;
import com.shopify.api.message.admin.user.AdminUserCreateResponse;
import com.shopify.api.message.admin.user.AdminUserDeleteReponse;
import com.shopify.api.message.admin.user.AdminUserDeleteRequest;
import com.shopify.api.message.admin.user.AdminUserListRequest;
import com.shopify.api.message.admin.user.AdminUserListResponse;
import com.shopify.api.message.admin.user.AdminUserUpdateRequest;
import com.shopify.api.message.admin.user.AdminUserUpdateResponse;
import com.shopify.api.models.branch.BranchEntity;
import com.shopify.api.models.role.Role;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.repository.branch.BranchRepository;
import com.shopify.api.repository.role.RoleRepository;
import com.shopify.api.repository.user.UserRepository;
import com.shopify.api.services.admin.user.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	/**
	 * User creation API, accessible only to 'ADMIN' users.
	 * @param adminUserCreateRequest The request object containing user creation details.
	 * @return ResponseEntity<AdminUserCreateResponse> Response entity containing the result of the user creation process.
	 * @throws UsernameAlreadyTakenException If the provided [user name] already exists in the repository.
	 * @throws Exception If any other unexpected error occurs during user creation.
	 */
	@Override
	public AdminUserCreateResponse create(AdminUserCreateRequest adminUserCreateRequest) {
		if (userRepository.existsByUsername(adminUserCreateRequest.getUsername())) {
			throw new UsernameAlreadyTakenException("Username is already Taken");
		}
		UserEntity user = new UserEntity();
		user.setUsername(adminUserCreateRequest.getUsername());
		user.setPassword(passwordEncoder.encode((adminUserCreateRequest.getPassword())));
		user.setPhone(adminUserCreateRequest.getPhone());

		List<Role> roles = new ArrayList<Role>();
		for (String role : adminUserCreateRequest.getRoles()) {
			roles.add(roleRepository.findByName(role).get());
		}
		user.setRoles(roles);
		Role userRole = roleRepository.findByName("USER").get();
		Role adminRole = roleRepository.findByName("ADMIN").get();
		if (user.getRoles().contains(userRole)) {
			BranchEntity branch = branchRepository.findByCode("101").get();
			user.setBranch(branch);
		}
		if (user.getRoles().contains(adminRole)) {
			BranchEntity branch = branchRepository.findByCode("000").get();
			user.setBranch(branch);
		}
		userRepository.save(user);
		
		AdminUserCreateResponse res = new AdminUserCreateResponse();
		res.setResponseFromUserEntity(user);
		return res;
	}

	/**
	 * User delete API, accessible only to 'ADMIN' users.
	 * @param AdminUserDeleteRequest The request object containing user creation details.
	 * @return AdminUserDeleteReponse, Response entity containing the result of the user delete process.
	 * @throws Exception If any other unexpected error occurs during user delete.
	 */
	@Override
	public AdminUserDeleteReponse deleteUser(AdminUserDeleteRequest adminUserDeleteRequest) {
		AdminUserDeleteReponse res = new AdminUserDeleteReponse();
		UserEntity user;
		if (adminUserDeleteRequest.getId() != null) {
			user = userRepository.findById(adminUserDeleteRequest.getId()).get();
			user.getRoles().clear();
			userRepository.deleteById(adminUserDeleteRequest.getId());
			res.setId(adminUserDeleteRequest.getId());
		} else if (adminUserDeleteRequest.getUid() != null) {
			user = userRepository.findByUid(adminUserDeleteRequest.getUid());
			user.getRoles().clear();
			userRepository.deleteByUid(adminUserDeleteRequest.getUid());
			res.setUid(adminUserDeleteRequest.getUid());
		} else {
			throw new UsernameNotFoundException("Delete User Not Found!");
		}
		return res;
	}

	/**
	 * User list all User API, accessible only to 'ADMIN' users.
	 * @param AdminUserListRequest The request object containing user creation details.
	 * @return AdminUserListResponse, Response entity containing the result.
	 * @throws Exception If any other unexpected error occurs during user fetching.
	 */
	@Override
	public List<AdminUserListResponse> listAll(AdminUserListRequest adminUserListRequest) {
		List<AdminUserListResponse> userList;
		List<UserEntity> users = userRepository.findAll();
		userList = AdminUserListResponse.UserEntityToUserListResponse(users);
		return userList;
	}

	/**
	 * Update user API, accessible only to 'ADMIN' users.
	 * @param AdminUserUpdateRequest The request object containing user creation details.
	 * @return AdminUserUpdateResponse, Response entity containing the result.
	 * @throws Exception If any other unexpected error occurs during user update.
	 */
	@Override
	public AdminUserUpdateResponse update(AdminUserUpdateRequest updateUserRequest) {
		AdminUserUpdateResponse res = new AdminUserUpdateResponse();
		UserEntity user = userRepository.findById(updateUserRequest.getId()).get();
		user = updateUserRequest.update(user);
		userRepository.save(user);
		res.setUsername(user.getUsername());
		return res;
	}
}
