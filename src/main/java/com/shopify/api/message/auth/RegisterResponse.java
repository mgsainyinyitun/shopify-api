package com.shopify.api.message.auth;

import com.shopify.api.models.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private String username;
    private String phone;
    private String uid;

    public RegisterResponse(UserEntity user){
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.uid = user.getUid();
    }

}
