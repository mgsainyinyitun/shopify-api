package com.shopify.api.repository.user;

import java.util.List;
import java.util.Optional;

import com.shopify.api.models.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shopify.api.models.user.UserEntity;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
	Optional<UserEntity> findByUsername(String username);
	UserEntity findByUid(String uid);
	Boolean existsByUsername(String username);

	Optional<UserEntity> deleteByUid(String uid);

	List<UserEntity> findAllByRoles(List<Role> roles);

}



