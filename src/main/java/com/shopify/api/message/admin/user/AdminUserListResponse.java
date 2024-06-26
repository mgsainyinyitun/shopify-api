package com.shopify.api.message.admin.user;

import java.util.ArrayList;
import java.util.List;

import com.shopify.api.models.user.UserEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdminUserListResponse {
	private Long id;
	private String username;
	private String phone;
	private Integer membership;
	private String country;
	private String language;
	private Double balance;
	private Double revenue;
	private String uid;
	private Boolean freeze;
	private String Error;

	public static List<AdminUserListResponse> UserEntityToUserListResponse(List<UserEntity> users) {
		List<AdminUserListResponse> list = new ArrayList<AdminUserListResponse>();
		for (UserEntity user : users) {
			list.add(convert(user));
		}
		return list;
	}

	private static AdminUserListResponse convert(UserEntity user) {
		AdminUserListResponse res = new AdminUserListResponse();
		res.setId(user.getId());
		res.setUsername(user.getUsername());
		res.setPhone(user.getPhone());
		res.setMembership(user.getMembership());
		res.setCountry(user.getCountry());
		res.setLanguage(user.getLanguage());
		res.setBalance(user.getBalance());
		res.setRevenue(user.getRevenue());
		res.setUid(user.getUid());
		res.setFreeze(user.isFreeze());
		return res;
	}
}
