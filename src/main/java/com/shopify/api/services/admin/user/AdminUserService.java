package com.shopify.api.services.admin.user;

import java.util.List;

import com.shopify.api.message.admin.user.*;

public interface AdminUserService {
	AdminUserCreateResponse create(AdminUserCreateRequest adminUserCreateRequest);

    AdminUserDeleteReponse deleteUser(AdminUserDeleteRequest adminUserDeleteRequest);
    
    List<AdminUserListResponse> listAll(AdminUserListRequest adminUserListRequest);
    
    AdminUserUpdateResponse update(AdminUserUpdateRequest updateUserRequest);

    AdminUsersListResponse list(AdminUserListRequest request);

    AdminTradeUserResponse detail(AdminTradeUserRequest request);
}
