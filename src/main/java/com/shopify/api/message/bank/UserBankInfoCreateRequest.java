package com.shopify.api.message.bank;

import com.shopify.api.models.bank.user.UserBankInfoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBankInfoCreateRequest {
    private String account;

    private String name;

    private String identification;

    private Long bankId;

    private Long userId;

    private  String email;

    private String phone;

    public UserBankInfoEntity toEntity(){
        UserBankInfoEntity userBank = new UserBankInfoEntity();
        userBank.setName(name);
        userBank.setIdentification(identification);
        userBank.setEmail(email);
        userBank.setPhone(phone);
        return userBank;
    }

}
