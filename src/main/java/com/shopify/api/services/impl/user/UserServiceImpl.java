package com.shopify.api.services.impl.user;

import com.shopify.api.models.user.UserEntity;
import com.shopify.api.repository.user.UserRepository;
import com.shopify.api.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }
}
