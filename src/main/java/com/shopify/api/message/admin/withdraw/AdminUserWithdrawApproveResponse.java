package com.shopify.api.message.admin.withdraw;

import com.shopify.api.constant.WITHDRAW;
import com.shopify.api.models.withdraw.WithdrawEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserWithdrawApproveResponse {
    private Long id;

    private String bank;

    private Double amount;

    private WITHDRAW status;
    public AdminUserWithdrawApproveResponse(WithdrawEntity withdraw) {
        this.id = withdraw.getId();
        this.bank = withdraw.getBank().getName();
        this.amount = withdraw.getAmount();
        this.status = withdraw.getStatus();
    }
}
