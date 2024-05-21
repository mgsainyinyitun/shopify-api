package com.shopify.api.message.admin.contract;

import com.shopify.api.models.contract.ContractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserContractRejectResponse {
    private Long contractId;

    public AdminUserContractRejectResponse(ContractEntity contract) {
        this.contractId = contract.getId();
    }
}
