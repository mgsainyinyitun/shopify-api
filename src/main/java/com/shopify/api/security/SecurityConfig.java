package com.shopify.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JWTAuthEntryPoint jwtAuthEntryPoint;

	public SecurityConfig(CustomUserDetailsService customUserDetailsService, JWTAuthEntryPoint jwtAuthEntryPoint) {
		this.setCustomUserDetailsService(customUserDetailsService);
		this.setJwtAuthEntryPoint(jwtAuthEntryPoint);
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//        .csrf().disable()
//        .exceptionHandling()
//        .authenticationEntryPoint(jwtAuthEntryPoint)
//        .and()
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .authorizeRequests()
//        .requestMatchers("/api/auth/**").permitAll()
//        .requestMatchers("/","home").permitAll()
////        .requestMatchers("/api/products").hasRole("ADMIN")
//        .anyRequest().authenticated()
//        .and()
//        .httpBasic();

		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((requests) -> requests.requestMatchers("/", "/home").permitAll()
						.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers("/api/merchant/**").authenticated()
						.requestMatchers("/api/image/**").authenticated()
						.requestMatchers("/api/user/**").hasAnyRole("ADMIN","USER")
						.requestMatchers("/api/product/**").hasAnyRole("ADMIN","USER")
						.requestMatchers("/api/contract/**").hasAnyRole("ADMIN","USER")
						.requestMatchers("/api/bank/**").hasAnyRole("ADMIN","USER")
						.requestMatchers("/api/admin/**").hasRole("ADMIN")
						.requestMatchers("/api/withdraw/**").permitAll()
//						.requestMatchers("/api/admin/").permitAll()
						.anyRequest().authenticated())
				.sessionManagement(
						management -> management
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(
						handling -> handling
						.authenticationEntryPoint(jwtAuthEntryPoint));
		http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	JWTAuthenticationFilter jwtAuthFilter() {
		return new JWTAuthenticationFilter();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public CustomUserDetailsService getCustomUserDetailsService() {
		return customUserDetailsService;
	}

	public void setCustomUserDetailsService(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}

	public JWTAuthEntryPoint getJwtAuthEntryPoint() {
		return jwtAuthEntryPoint;
	}

	public void setJwtAuthEntryPoint(JWTAuthEntryPoint jwtAuthEntryPoint) {
		this.jwtAuthEntryPoint = jwtAuthEntryPoint;
	}
}
