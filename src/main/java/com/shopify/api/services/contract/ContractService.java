package com.shopify.api.services.contract;

import com.shopify.api.message.contract.*;
import com.shopify.api.models.user.UserEntity;

public interface ContractService {
    ContractSignResponse signContract(ContractSignRequest request, UserEntity user);

    ContractListResponse listContracts(ContractListRequest request,UserEntity user);

    ContractResponse getCurrentContract(ContractRequest request);

}
