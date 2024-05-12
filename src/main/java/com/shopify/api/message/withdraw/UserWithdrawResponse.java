package com.shopify.api.message.withdraw;

import com.shopify.api.constant.WITHDRAW;
import com.shopify.api.models.bank.BankInfoEntity;
import com.shopify.api.models.withdraw.WithdrawEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithdrawResponse {
    private Long id;

    private String bankUsername;

    private  String username;

    private Double amount;

    private WITHDRAW status;

    private String account;

    private String identification;

    private String email;

    private String phone;

    private BankInfoEntity bankInfo;

    public UserWithdrawResponse(WithdrawEntity withdraw) {
        this.id = withdraw.getId();
        this.bankUsername = withdraw.getBank().getName();
        this.username = withdraw.getUser().getUsername();
        this.amount = withdraw.getAmount();
        this.status = withdraw.getStatus();
        this.account = withdraw.getBank().getAccount();
        this.identification = withdraw.getBank().getIdentification();
        this.email = withdraw.getBank().getEmail();
        this.phone = withdraw.getBank().getPhone();
        this.bankInfo = withdraw.getBank().getBank();
    }
}
