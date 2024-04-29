package com.shopify.api.models.contract;


import com.shopify.api.constant.CONTRACT_STATUS;
import com.shopify.api.models.merchant.MerchantEntity;
import com.shopify.api.models.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "contract")
public class ContractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "merchant_id",unique = true)
    private MerchantEntity merchant;

    @Enumerated(EnumType.STRING)
    private CONTRACT_STATUS status;

    private boolean taskComplete=false;

    private Integer currentTask=1;

    private Integer totalTask;

    private  Integer finishedTask=0;

    @ManyToOne
    @JoinColumn(name = "approve_by")
    private  UserEntity approveBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = true)
    private LocalDateTime updatedAt;
}
