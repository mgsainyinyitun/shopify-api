package com.shopify.api.message.admin.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserBalanceIncreaseRequest {
    private String uid;
    private Double amount;
}
