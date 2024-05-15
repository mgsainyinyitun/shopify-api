package com.shopify.api.services.impl.bank;

import com.shopify.api.exceptions.BankInfoAlreadyExistException;
import com.shopify.api.message.bank.*;
import com.shopify.api.models.bank.BankInfoEntity;
import com.shopify.api.models.bank.user.UserBankInfoEntity;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.repository.bank.BankInfoRepository;
import com.shopify.api.repository.bank.UserBankInfoRepository;
import com.shopify.api.repository.user.UserRepository;
import com.shopify.api.services.bank.UserBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserBankInfoServiceImpl implements UserBankInfoService {

    @Autowired
    UserBankInfoRepository userBankInfoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BankInfoRepository bankInfoRepository;

    @Override
    public UserBankInfoCreateResponse createUserBankInfo(UserBankInfoCreateRequest request) {

        List<UserBankInfoEntity> usrBank = userBankInfoRepository.findByName(request.getName());

        if(usrBank != null){
            for(UserBankInfoEntity uBank : usrBank){
                if(Objects.equals(uBank.getBank().getId(), request.getBankId())){
                    throw new BankInfoAlreadyExistException("User already register with that name!");
                }
            }
        }

        UserBankInfoEntity usrBankInfo = request.toEntity();

        UserEntity user = userRepository.findById(request.getUserId()).get();
        BankInfoEntity bankInfo = bankInfoRepository.findById(request.getBankId()).get();
        usrBankInfo.setUser(user);
        usrBankInfo.setBank(bankInfo);
        userBankInfoRepository.save(usrBankInfo);
        return new UserBankInfoCreateResponse(usrBankInfo);
    }

    @Override
    public UserBankInfoListResponse getAllUserBankInfos(UserBankInfoListRequest request,UserEntity user) {
        List<UserBankInfoResponse> bankInfoLst = new ArrayList<>();
        List<UserBankInfoEntity> dbBankInfoLst;

        dbBankInfoLst =  userBankInfoRepository.findAllByUserId(user.getId());

        for(UserBankInfoEntity usrBankInfo : dbBankInfoLst){
            bankInfoLst.add(new UserBankInfoResponse(usrBankInfo));
        }
        UserBankInfoListResponse response = new UserBankInfoListResponse();
        response.setBanks(bankInfoLst);
        return response;
    }
}
