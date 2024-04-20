package com.shopify.api.message.bank;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserBankInfoListResponse {
    List<UserBankInfoResponse> banks;
}
