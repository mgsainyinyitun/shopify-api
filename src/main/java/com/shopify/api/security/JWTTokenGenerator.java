package com.shopify.api.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenGenerator {

	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date currentDate = new Date();

		Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
		
		Date expirationDate = new Date(currentDate.getTime() + 10 * 60 * 1000);

//		Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
//		String token = Jwts.builder()
//				.setSubject(username).setIssuedAt(currentDate).setExpiration(expireDate)
//				.signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET).compact();
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt( new Date())
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512,SecurityConstants.JWT_SECRET)
				.compact();
		
		System.out.println("New token :");
		System.out.println(token);
		System.out.println("Expire Date:");
		System.out.println(expireDate.toString());

		return token;
	}
	
//	public String generateToken(String username) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, username);
//    }
	
//	private String createToken(Map<String, Object> claims, String subject) {
//
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_SECRET).compact();
//    }

	public String getUsernamefromJwt(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(SecurityConstants.JWT_SECRET)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	void expireDate(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(SecurityConstants.JWT_SECRET)
				.build()
				.parseClaimsJws(token)
				.getBody();
		Date expirationDate = claims.getExpiration();

        System.out.println("Expiration Date/Time: " + expirationDate);
	}
	
	
	public boolean validateToken(String token)throws AuthenticationCredentialsNotFoundException {
		try {
			Jwts.parserBuilder()
			.setSigningKey(SecurityConstants.JWT_SECRET)
			.build()
			.parseClaimsJws(token);
			return true;
		} 
		
		catch (Exception ex) {
			return false;
//			throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
		}
	}
	

	

}
