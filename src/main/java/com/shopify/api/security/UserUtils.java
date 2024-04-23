package com.shopify.api.security;

import com.shopify.api.models.user.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
   public static String getLoginUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;
        if (authentication != null) {
            username = authentication.getName();
        } else {
            username = null;
        }
        return username;
    }
}
