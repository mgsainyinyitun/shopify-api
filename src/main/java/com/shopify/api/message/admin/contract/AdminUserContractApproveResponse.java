package com.shopify.api.message.admin.contract;

import com.shopify.api.constant.CONTRACT_STATUS;
import com.shopify.api.models.contract.ContractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminUserContractApproveResponse {
    private Long id;
    private CONTRACT_STATUS status;
    private boolean taskComplete=false;
    private Integer currentTask=1;
    private Integer totalTask;
    private  Integer finishedTask=0;

    public AdminUserContractApproveResponse(ContractEntity contract) {
        this.id = contract.getId();
        this.status = contract.getStatus();
        this.taskComplete = contract.isTaskComplete();
        this.currentTask = contract.getCurrentTask();
        this.totalTask = contract.getTotalTask();
        this.finishedTask = contract.getFinishedTask();
    }
}
