package com.shopify.api.models.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.shopify.api.models.image.ImageEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.shopify.api.models.branch.BranchEntity;
import com.shopify.api.models.role.Role;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", nullable = false, updatable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	private String phone;

	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean freeze = false;

	private int membership=1;

	private String country;

	private String language;

	@Column(columnDefinition = "DOUBLE DEFAULT 0.0")
	private Double balance = 10.0;

	@Column(columnDefinition = "DOUBLE DEFAULT 0.0")
	private Double revenue = 0.0;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(nullable = true,name = "image_id")
	private ImageEntity image;

	@PostPersist
	protected void onCreate() {
		String uuid = UUID.randomUUID().toString();
		String ID = String.valueOf(id);
		String name = this.getClass().getSimpleName().replace("Entity","");
		iuid = name+ ID + uuid;
		uid = this.branch.getCode() + String.format("%06d",id);
	}

	@Column(name = "uid", nullable = true, unique = true)
	private String uid;

	@Column(name = "iuid",nullable = true,unique = true)
	private String iuid;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false, updatable = true)
	private LocalDateTime updatedAt;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = true)

	private BranchEntity branch;
}
