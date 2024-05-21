package com.shopify.api.message.admin.user;

import com.shopify.api.constant.CONTRACT_STATUS;
import com.shopify.api.models.contract.ContractEntity;
import com.shopify.api.models.merchant.MerchantEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdminUserContractHistoryResponse {
    private Long id;
    private MerchantEntity merchant;

    private CONTRACT_STATUS status;

    private boolean taskComplete;

    private Integer totalTask;

    private  Integer finishedTask=0;

    private LocalDateTime contractAt;

    public AdminUserContractHistoryResponse(ContractEntity contract) {
        this.merchant = contract.getMerchant();
        this.id = contract.getId();
        this.status = contract.getStatus();
        this.taskComplete = contract.isTaskComplete();
        this.totalTask = contract.getTotalTask();
        this.finishedTask = contract.getFinishedTask();
        this.contractAt = contract.getCreatedAt();
    }
}
