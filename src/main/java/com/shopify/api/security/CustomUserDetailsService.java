package com.shopify.api.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopify.api.models.role.Role;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.repository.user.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	private String ROLE_PREFIX = "ROLE_";

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
		return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
//		auths.add(new SimpleGrantedAuthority(ROLE_PREFIX+user.getProfile().getName()));
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(ROLE_PREFIX+role.getName())).collect(Collectors.toList());
	}
}
