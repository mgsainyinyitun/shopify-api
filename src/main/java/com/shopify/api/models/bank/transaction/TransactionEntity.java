package com.shopify.api.models.bank.transaction;

import com.shopify.api.constant.TRANSACTION_STATUS;
import com.shopify.api.constant.TRANSACTION_TYPE;
import com.shopify.api.models.bank.user.UserBankInfoEntity;
import com.shopify.api.models.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TRANSACTION_STATUS transactionStatus;

    @Enumerated(EnumType.STRING)
    private TRANSACTION_TYPE transactionType;

    private  Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "user_bank_info_id")
    private UserBankInfoEntity userBankInfo;
}
