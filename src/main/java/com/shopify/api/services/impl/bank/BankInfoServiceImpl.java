package com.shopify.api.services.impl.bank;

import com.shopify.api.message.admin.bank.BankInfoCreateRequest;
import com.shopify.api.message.admin.bank.BankInfoCreateResponse;
import com.shopify.api.message.bank.BankInfoListRequest;
import com.shopify.api.message.bank.BankInfoListResponse;
import com.shopify.api.message.bank.BankInfoResponse;
import com.shopify.api.models.bank.BankInfoEntity;
import com.shopify.api.repository.bank.BankInfoRepository;
import com.shopify.api.services.bank.BankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankInfoServiceImpl implements BankInfoService {

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Override
    public BankInfoListResponse getBankInfoList(BankInfoListRequest request) {
        BankInfoListResponse response = new BankInfoListResponse();
        List<BankInfoResponse> banksInfo = new ArrayList<BankInfoResponse>();

        List<BankInfoEntity> bankInfoEntities = bankInfoRepository.findAll();

        for (BankInfoEntity bkInfo : bankInfoEntities) {
            banksInfo.add(new BankInfoResponse(bkInfo));
        }
        response.setBanks(banksInfo);
        return response;
    }

    @Override
    public BankInfoCreateResponse createBankInfo(BankInfoCreateRequest request) {
        BankInfoEntity bankInfo = new BankInfoEntity();
        bankInfo.setName(request.getName());

        bankInfoRepository.save(bankInfo);
        return new BankInfoCreateResponse(bankInfo);
    }
}
