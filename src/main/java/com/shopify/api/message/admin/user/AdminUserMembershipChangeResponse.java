package com.shopify.api.message.admin.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserMembershipChangeResponse {
    private  Long userId;
    private Integer membership;
}
