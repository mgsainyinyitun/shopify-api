package com.shopify.api.message.withdraw;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserWithdrawRequest {
    private String uid;

    private Long userBankInfoId;

    private Double amount;
}
