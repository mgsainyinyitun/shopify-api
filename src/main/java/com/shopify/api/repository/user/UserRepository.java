package com.shopify.api.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopify.api.models.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
	Optional<UserEntity> findByUsername(String username);
	Optional<UserEntity> findByUid(String uid);
	Boolean existsByUsername(String username);

	Optional<UserEntity> deleteByUid(String uid);

}
