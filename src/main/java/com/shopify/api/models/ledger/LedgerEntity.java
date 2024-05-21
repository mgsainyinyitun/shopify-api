package com.shopify.api.models.ledger;

import com.shopify.api.constant.LEDGER;
import com.shopify.api.models.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LedgerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private LEDGER type;

    private Double amount;
}

