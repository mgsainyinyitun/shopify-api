package com.shopify.api.controller.auth;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.api.dto.auth.AuthResponseDTO;
import com.shopify.api.dto.auth.LoginDTO;
import com.shopify.api.dto.auth.RegisterDTO;
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
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BranchRepository branchRepository;
	private PasswordEncoder passwordEncoder;
	private JWTTokenGenerator jwtTokenGenerator;

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
	public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenGenerator.generateToken(authentication);

		UserEntity user = userRepository.findByUsername(loginDto.getUsername()).get();

		AuthResponseDTO authDTO = new AuthResponseDTO(token);
		authDTO.setUsername(user.getUsername());
		authDTO.setPhone(user.getPhone());
		authDTO.setUid(user.getUid());

		return new ResponseEntity<>(authDTO, HttpStatus.OK);
	}

	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto) {
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
		}

		UserEntity user = new UserEntity();
		user.setUsername(registerDto.getUsername());
		user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
		user.setPhone(registerDto.getPhone());

		Role roles = roleRepository.findByName("USER").get();
		BranchEntity branch = branchRepository.findByCode("101").get();

		user.setRoles(Collections.singletonList(roles));
		user.setBranch(branch);
		userRepository.save(user);
		return new ResponseEntity<>("User registered success!", HttpStatus.OK);
	}
}
