package com.shopify.api.message.admin.bank;

import com.shopify.api.models.bank.BankInfoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankInfoCreateResponse {
    private Long id;

    private String name;

    private String uid;

    public BankInfoCreateResponse(BankInfoEntity bankInfo) {
        this.id = bankInfo.getId();
        this.name = bankInfo.getName();
        this.uid = bankInfo.getUid();
    }
}
