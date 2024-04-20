package com.shopify.api.message.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String username;
    private String phone;
    private Long uid;
    private String accessToken;
    private String tokenType = "Bearer ";

    public LoginResponse(String token){
        this.accessToken = token;
    }
}
