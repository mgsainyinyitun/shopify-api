package com.shopify.api.models.withdraw;

import com.shopify.api.constant.WITHDRAW;
import com.shopify.api.models.bank.user.UserBankInfoEntity;
import com.shopify.api.models.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "withdraw")
public class WithdrawEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private WITHDRAW status;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "bank")
    private UserBankInfoEntity bank;

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
