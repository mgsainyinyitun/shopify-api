package com.shopify.api.security;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Keys;

public class SecurityConstants {
	public static final long JWT_EXPIRATION = 70000;
//	public static final String JWT_SECRET = "Z0GI027JHEEY9EVSSRRX3Q7UTMBN4P";
	
	public static final Key JWT_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
//	public static final Key JWT_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS512);
}
