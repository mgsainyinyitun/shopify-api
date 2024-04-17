package com.shopify.api.repository.role;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shopify.api.models.role.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(String username);
}
