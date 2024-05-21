package com.shopify.api.message.admin.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserMembershipChangeRequest {
    private  String uid;
    private Integer membership;
}
