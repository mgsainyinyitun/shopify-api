package com.shopify.api.services.user;

import com.shopify.api.models.user.UserEntity;

public interface UserService {
    UserEntity getUserByUsername(String username);
}
