package com.shopify.api.message.contract;

import com.shopify.api.constant.CONTRACT_STATUS;
import com.shopify.api.models.contract.ContractEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContractSignResponse {
    private Long id;

    private Long merchantId;

    private CONTRACT_STATUS status;

    private LocalDateTime requestTime;

    public ContractSignResponse(ContractEntity contract){
        this.id = contract.getId();
        this.merchantId = contract.getMerchant().getId();
        this.status = contract.getStatus();
        this.requestTime = contract.getCreatedAt();
    }
}
