package com.shopify.api.message.bank;


import com.shopify.api.models.bank.user.UserBankInfoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBankInfoCreateResponse {
    private Long id;

    private String account;

    private String name;

    private String identification;

    private Long userId;

    private Long bankId;

    private String email;

    private String phone;

    public UserBankInfoCreateResponse(UserBankInfoEntity usrBankInfo){
        this.id = usrBankInfo.getId();
        this.name = usrBankInfo.getName();
        this.identification = usrBankInfo.getIdentification();
        this.setEmail(usrBankInfo.getEmail());
        this.setPhone(usrBankInfo.getPhone());
        this.setUserId(usrBankInfo.getUser().getId());
        this.setBankId(usrBankInfo.getBank().getId());
    }

}
