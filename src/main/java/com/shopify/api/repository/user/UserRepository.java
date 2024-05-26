package com.shopify.api.repository.user;

import java.util.List;
import java.util.Optional;

import com.shopify.api.models.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shopify.api.models.user.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
	
	@Query( "SELECT u FROM UserEntity u where u.username=:username or u.phone = :username")
	Optional<UserEntity> findByUsername(@Param("username") String username);
	
	UserEntity findByUid(String uid);
	Boolean existsByUsername(String username);

	Optional<UserEntity> deleteByUid(String uid);

	List<UserEntity> findAllByRoles(List<Role> roles);

}



