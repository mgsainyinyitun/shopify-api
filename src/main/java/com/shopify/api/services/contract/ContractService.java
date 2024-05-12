package com.shopify.api.services.contract;

import com.shopify.api.message.admin.contract.*;
import com.shopify.api.message.admin.user.AdminUserContractHistListRequest;
import com.shopify.api.message.admin.user.AdminUserContractHistListResponse;
import com.shopify.api.message.contract.*;
import com.shopify.api.models.user.UserEntity;

public interface ContractService {
    ContractSignResponse signContract(ContractSignRequest request, UserEntity user);

    ContractListResponse listContracts(ContractListRequest request,UserEntity user);

    ContractResponse getCurrentContract(ContractRequest request);

    AdminUserContractHistListResponse getContractsList(AdminUserContractHistListRequest request);

    AdminUserContractListResponse getUsersContracts(AdminUserContractListRequest request);

    AdminUserContractApproveResponse  contractApprove(AdminUserContractApproveRequest request);

    AdminUserContractRejectResponse contractReject(AdminUserContractRejectRequest request);
}
