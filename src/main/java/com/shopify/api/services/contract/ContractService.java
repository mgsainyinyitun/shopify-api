package com.shopify.api.services.contract;

import com.shopify.api.message.contract.ContractListRequest;
import com.shopify.api.message.contract.ContractListResponse;
import com.shopify.api.message.contract.ContractSignRequest;
import com.shopify.api.message.contract.ContractSignResponse;
import com.shopify.api.models.user.UserEntity;

public interface ContractService {
    ContractSignResponse signContract(ContractSignRequest request, UserEntity user);

    ContractListResponse listContracts(ContractListRequest request,UserEntity user);
}
