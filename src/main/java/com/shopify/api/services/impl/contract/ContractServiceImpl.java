package com.shopify.api.services.impl.contract;

import com.shopify.api.constant.CONTRACT_STATUS;
import com.shopify.api.exceptions.BalanceInsufficientException;
import com.shopify.api.exceptions.UserMembershipInsufficientException;
import com.shopify.api.message.contract.*;
import com.shopify.api.models.contract.ContractEntity;
import com.shopify.api.models.merchant.MerchantEntity;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.repository.contract.ContractRepository;
import com.shopify.api.repository.merchant.MerchantRepository;
import com.shopify.api.services.contract.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractRepository contractRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public ContractSignResponse signContract(ContractSignRequest request, UserEntity user) {
        MerchantEntity merchant = merchantRepository.findById(request.getMerchantId()).get();
        int blCmp = user.getBalance().compareTo(merchant.getLowerLimit());
        if(blCmp<0){
            throw  new BalanceInsufficientException("User balance is lower than lower limit!");
        }else if(user.getMembership()<merchant.getRating()){
            throw new UserMembershipInsufficientException("User membership rating is lower than Merchant min-rating!");
        }
        ContractEntity contract = new ContractEntity();
        contract.setMerchant(merchant);
        contract.setUser(user);
        contract.setStatus(CONTRACT_STATUS.PENDING);
        contract.setTotalTask(12);
        contract.setCurrentTask(1);
        contract.setFinishedTask(0);
        contractRepository.save(contract);
        return new ContractSignResponse(contract);
    }

    @Override
    public ContractListResponse listContracts(ContractListRequest request,UserEntity user) {
        List<ContractEntity> contracts = contractRepository.findAllByUserId(user.getId());
        List<ContractResponse> resContracts = new ArrayList<>();
        for(ContractEntity contract : contracts){
            resContracts.add(new ContractResponse(contract));
        }
        ContractListResponse response = new ContractListResponse();
        response.setContracts(resContracts);
        return response;
    }

    @Override
    public ContractResponse getCurrentContract(ContractRequest request) {
        ContractEntity contract;
        if(request.getMerchantId()!=null){
            MerchantEntity merchant = merchantRepository.findById(request.getMerchantId()).get();
            contract = contractRepository.findCurrentContractByMerchant(merchant);
        }else{
            contract = contractRepository.findCurrentContract();
        }
        if(contract==null){
            return  null;
        }
        return new ContractResponse(contract);
    }
}
