package com.shopify.api.models.bank.user;

import com.shopify.api.models.bank.BankInfoEntity;
import com.shopify.api.models.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_bank_info")
public class UserBankInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    private String identification;

    private String email;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "bank_info_id",unique = true)
    private BankInfoEntity bank;
}
