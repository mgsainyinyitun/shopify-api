package com.shopify.api.message.admin.user;

import com.shopify.api.models.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdminUserResponse {
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
    private LocalDateTime registerAt;

    public AdminUserResponse(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.membership = user.getMembership();
        this.country = user.getCountry();
        this.language = user.getLanguage();
        this.balance = user.getBalance();
        this.revenue = user.getRevenue();
        this.uid = user.getUid();
        this.freeze = user.isFreeze();
        this.registerAt = user.getCreatedAt();
    }
}
