package com.shopify.api.message.bank;

import com.shopify.api.models.bank.BankInfoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BankInfoResponse {
    private Long id;

    private String name;

    private String imageId;

    public BankInfoResponse(BankInfoEntity bankInfoEntity){
        this.id = bankInfoEntity.getId();
        this.name = bankInfoEntity.getName();
        if(bankInfoEntity.getImage() != null){
            this.imageId = bankInfoEntity.getImage().getUuid();
        }
    }
}
