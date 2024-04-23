package com.shopify.api.models.merchant;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.shopify.api.models.image.ImageEntity;
import com.shopify.api.models.product.ProductEntity;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "merchants")
public class MerchantEntity {
	public MerchantEntity() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false,unique = true)
	private String name;

	private String description;
	private int rating;

	private Double lowerLimit;

	private String uid;

	@PostPersist
	protected void onCreate() {
		String uuid = UUID.randomUUID().toString();
		String ID = String.valueOf(id);
		String name = this.getClass().getSimpleName().replace("Entity","");
		uid = name+ ID + uuid;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "image_id",nullable = true)
	private ImageEntity image;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false, updatable = true)
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductEntity> products;
}
