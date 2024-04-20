package com.shopify.api.message.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContractListResponse {
    List<ContractResponse> contracts;
}
