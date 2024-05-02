package com.shopify.api.message.admin.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminUsersListResponse {
    List<AdminUserResponse> users;
}
