package com.shopify.api.services.admin.user;

import java.util.List;

import com.shopify.api.message.admin.user.AdminUserCreateRequest;
import com.shopify.api.message.admin.user.AdminUserCreateResponse;
import com.shopify.api.message.admin.user.AdminUserDeleteReponse;
import com.shopify.api.message.admin.user.AdminUserDeleteRequest;
import com.shopify.api.message.admin.user.AdminUserListRequest;
import com.shopify.api.message.admin.user.AdminUserListResponse;
import com.shopify.api.message.admin.user.AdminUserUpdateRequest;
import com.shopify.api.message.admin.user.AdminUserUpdateResponse;

public interface AdminUserService {
	AdminUserCreateResponse create(AdminUserCreateRequest adminUserCreateRequest);

    AdminUserDeleteReponse deleteUser(AdminUserDeleteRequest adminUserDeleteRequest);
    
    List<AdminUserListResponse> listAll(AdminUserListRequest adminUserListRequest);
    
    AdminUserUpdateResponse update(AdminUserUpdateRequest updateUserRequest);
}
