package com.shopify.api.models.product;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.shopify.api.models.image.ImageEntity;
import com.shopify.api.models.merchant.MerchantEntity;

@Getter
@Setter
@Entity
@Table(name="products")
public class ProductEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name",nullable = false)
    private String name;
    
    private String description;

	private  Double commission;

	private String uid;

	private Double price=0.0;

	private Integer rating=1;

	@PostPersist
	protected void onCreate() {
		String uuid = UUID.randomUUID().toString();
		String ID = String.valueOf(id);
		String name = this.getClass().getSimpleName().replace("Entity","");
		uid = name+ ID + uuid;
	}

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private MerchantEntity merchant;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "image_id",nullable = true)
	private ImageEntity image;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false, updatable = true)
	private LocalDateTime updatedAt;
}
