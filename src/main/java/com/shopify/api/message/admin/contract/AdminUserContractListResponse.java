package com.shopify.api.message.admin.contract;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class AdminUserContractListResponse {
    List<UserContractInfoResponse> contracts;
}
