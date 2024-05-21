package com.shopify.api.message.admin.withdraw;

import com.shopify.api.message.withdraw.UserWithdrawResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminUserWithdrawListResponse {
    List<UserWithdrawResponse> withdraws;
}
