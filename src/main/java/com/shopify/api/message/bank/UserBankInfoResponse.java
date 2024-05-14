package com.shopify.api.message.bank;

import com.shopify.api.models.bank.user.UserBankInfoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBankInfoResponse {
    private Long id;

    private String account;

    private String name;

    private String identification;

    private String email;

    private String phone;

    private BankInfoResponse bank = new BankInfoResponse();

    public UserBankInfoResponse(UserBankInfoEntity usrBankInfo) {
        this.id = usrBankInfo.getId();
        this.name = usrBankInfo.getName();
        this.identification = usrBankInfo.getIdentification();
        this.email = usrBankInfo.getEmail();
        this.phone = usrBankInfo.getPhone();
        this.bank.setId(usrBankInfo.getBank().getId());
        this.bank.setName(usrBankInfo.getBank().getName());
        this.bank.setImageId(usrBankInfo.getBank().getImage().getUuid());
    }
}
