package com.shopify.api.message.admin.contract;

import com.shopify.api.constant.CONTRACT_STATUS;
import com.shopify.api.message.merchant.MerchantResponse;
import com.shopify.api.models.contract.ContractEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserContractInfoResponse {
    private Long id;
    private MerchantResponse merchant;

    private CONTRACT_STATUS status;

    private boolean taskComplete;

    private Integer totalTask;

    private  Integer finishedTask=0;

    private LocalDateTime contractAt;

    public UserContractInfoResponse(ContractEntity contract) {
        this.merchant = new MerchantResponse(contract.getMerchant());
        this.id = contract.getId();
        this.status = contract.getStatus();
        this.taskComplete = contract.isTaskComplete();
        this.totalTask = contract.getTotalTask();
        this.finishedTask = contract.getFinishedTask();
        this.contractAt = contract.getCreatedAt();
    }
}
