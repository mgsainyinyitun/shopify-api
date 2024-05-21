package com.shopify.api.message.admin.withdraw;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserWithdrawApproveRequest {
    private String uid;

    private Long withdrawId;
}
