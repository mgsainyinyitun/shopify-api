package com.shopify.api.services.withdraw;

import com.shopify.api.message.admin.withdraw.*;
import com.shopify.api.message.withdraw.UserWithdrawListRequest;
import com.shopify.api.message.withdraw.UserWithdrawListResponse;
import com.shopify.api.message.withdraw.UserWithdrawRequest;
import com.shopify.api.message.withdraw.UserWithdrawResponse;
import com.shopify.api.models.user.UserEntity;

public interface WithdrawService {
    UserWithdrawResponse withdraw(UserWithdrawRequest request);
    UserWithdrawListResponse all(UserWithdrawListRequest request, UserEntity user);
    AdminUserWithdrawListResponse adminList(AdminUserWithdrawListRequest request);
    AdminUserWithdrawApproveResponse approveRequest(AdminUserWithdrawApproveRequest request);
    AdminUserWithdrawRejectResponse rejectRequest(AdminUserWithdrawRejectRequest request);
}
