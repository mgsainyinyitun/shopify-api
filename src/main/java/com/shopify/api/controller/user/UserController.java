package com.shopify.api.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.api.dto.user.UserDTO;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.repository.user.UserRepository;
import com.shopify.api.security.JWTTokenGenerator;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JWTTokenGenerator jwtTokenGenerator;
	
	@GetMapping("/details")
	public ResponseEntity<UserDTO> userDetails(@RequestHeader("Authorization") String authorizationHeader) {
		String token = authorizationHeader.replace("Bearer ", "");
		String username = jwtTokenGenerator.getUsernamefromJwt(token);
		UserEntity user = userRepository.findByUsername(username).get();
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}
}
