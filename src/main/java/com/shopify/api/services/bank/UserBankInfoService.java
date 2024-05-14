package com.shopify.api.services.bank;

import com.shopify.api.message.bank.UserBankInfoCreateRequest;
import com.shopify.api.message.bank.UserBankInfoCreateResponse;
import com.shopify.api.message.bank.UserBankInfoListRequest;
import com.shopify.api.message.bank.UserBankInfoListResponse;
import com.shopify.api.models.user.UserEntity;

public interface UserBankInfoService {

    UserBankInfoCreateResponse createUserBankInfo(UserBankInfoCreateRequest request);

    UserBankInfoListResponse getAllUserBankInfos(UserBankInfoListRequest request, UserEntity user);

}