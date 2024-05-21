package com.shopify.api.message.contract;

import com.shopify.api.constant.CONTRACT_STATUS;
import com.shopify.api.message.merchant.MerchantResponse;
import com.shopify.api.models.contract.ContractEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContractResponse {
    private Long id;

    private MerchantResponse merchant;

    private CONTRACT_STATUS status;

    private Integer totalTasks;

    private Integer currentTask;

    private Integer finishedTasks;

    private LocalDateTime requestTime;

    public ContractResponse(ContractEntity contract){
        this.id = contract.getId();
        this.merchant =  new MerchantResponse(contract.getMerchant());
        this.status = contract.getStatus();
        this.requestTime = contract.getCreatedAt();
        this.totalTasks = contract.getTotalTask();
        this.currentTask = contract.getCurrentTask();
        this.finishedTasks = contract.getFinishedTask();
    }
}
