package com.shopify.api.message.admin.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserContractApproveRequest {
    private String uid;

    private Long contractId;
}
