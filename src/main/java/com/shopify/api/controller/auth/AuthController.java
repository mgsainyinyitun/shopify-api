package com.shopify.api.controller.auth;

import java.util.Collections;

import com.shopify.api.message.auth.LoginRequest;
import com.shopify.api.message.auth.LoginResponse;
import com.shopify.api.message.auth.RegisterRequest;
import com.shopify.api.message.auth.RegisterResponse;
import com.shopify.api.message.error.ErrorMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shopify.api.models.branch.BranchEntity;
import com.shopify.api.models.role.Role;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.repository.branch.BranchRepository;
import com.shopify.api.repository.role.RoleRepository;
import com.shopify.api.repository.user.UserRepository;
import com.shopify.api.security.JWTTokenGenerator;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="*")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BranchRepository branchRepository;
	private final PasswordEncoder passwordEncoder;
	private final JWTTokenGenerator jwtTokenGenerator;

	public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTTokenGenerator jwtTokenGenerator,
			BranchRepository branchRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenGenerator = jwtTokenGenerator;
		this.branchRepository = branchRepository;
	}
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String token = jwtTokenGenerator.generateToken(authentication);

			UserEntity user = userRepository.findByUsername(request.getUsername()).get();

			LoginResponse response = new LoginResponse(token);
			response.setUsername(user.getUsername());
			response.setPhone(user.getPhone());
			response.setUid(user.getId());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch (Exception e){
			ErrorMessageResponse errorResponse = new ErrorMessageResponse("ERROR",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
		}

		UserEntity user = new UserEntity();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode((request.getPassword())));
		user.setPhone(request.getPhone());

		Role roles = roleRepository.findByName("USER").get();
		BranchEntity branch = branchRepository.findByCode("101").get();

		user.setRoles(Collections.singletonList(roles));
		user.setBranch(branch);
		userRepository.save(user);

		RegisterResponse response = new RegisterResponse(user);
		return ResponseEntity.ok(response);
	}
}
