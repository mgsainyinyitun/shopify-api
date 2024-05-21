package com.shopify.api.services.bank;

import com.shopify.api.message.admin.bank.BankInfoCreateRequest;
import com.shopify.api.message.admin.bank.BankInfoCreateResponse;
import com.shopify.api.message.bank.BankInfoListRequest;
import com.shopify.api.message.bank.BankInfoListResponse;

public interface BankInfoService {
    BankInfoListResponse getBankInfoList(BankInfoListRequest request);

    BankInfoCreateResponse createBankInfo(BankInfoCreateRequest request);
}
