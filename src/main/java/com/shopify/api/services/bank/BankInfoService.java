package com.shopify.api.services.bank;

import com.shopify.api.message.bank.BankInfoListRequest;
import com.shopify.api.message.bank.BankInfoListResponse;

public interface BankInfoService {
    BankInfoListResponse getBankInfoList(BankInfoListRequest request);
}
