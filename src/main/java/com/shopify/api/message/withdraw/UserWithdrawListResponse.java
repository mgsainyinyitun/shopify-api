package com.shopify.api.message.withdraw;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserWithdrawListResponse {
    List<UserWithdrawResponse> withdraws;
}
