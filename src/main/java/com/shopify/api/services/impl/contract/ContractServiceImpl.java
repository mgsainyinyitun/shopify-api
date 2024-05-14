package com.shopify.api.services.impl.contract;

import com.shopify.api.constant.CONTRACT_STATUS;
import com.shopify.api.constant.TRADING_STATE;
import com.shopify.api.exceptions.BalanceInsufficientException;
import com.shopify.api.exceptions.MerchantAlreadyApprovedException;
import com.shopify.api.exceptions.UserMembershipInsufficientException;
import com.shopify.api.message.admin.contract.*;
import com.shopify.api.message.admin.user.AdminUserContractHistListRequest;
import com.shopify.api.message.admin.user.AdminUserContractHistListResponse;
import com.shopify.api.message.admin.user.AdminUserContractHistoryResponse;
import com.shopify.api.message.contract.*;
import com.shopify.api.models.contract.ContractEntity;
import com.shopify.api.models.merchant.MerchantEntity;
import com.shopify.api.models.product.ProductEntity;
import com.shopify.api.models.trade.TradeHistoryEntity;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.repository.TradeHistoryRepository;
import com.shopify.api.repository.contract.ContractRepository;
import com.shopify.api.repository.merchant.MerchantRepository;
import com.shopify.api.repository.product.ProductRepository;
import com.shopify.api.services.contract.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractRepository contractRepository;

    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TradeHistoryRepository tradeHistoryRepository;

    @Override
    public ContractSignResponse signContract(ContractSignRequest request, UserEntity user) {
        MerchantEntity merchant = merchantRepository.findById(request.getMerchantId()).get();
        int blCmp = user.getBalance().compareTo(merchant.getLowerLimit());
        if(blCmp<0){
            throw  new BalanceInsufficientException("User balance is lower than lower limit!");
        }else if(user.getMembership()<merchant.getRating()){
            throw new UserMembershipInsufficientException("User membership rating is lower than Merchant min-rating!");
        }
        if(contractRepository.checkSignedContract(merchant,user)!=null){
            throw new MerchantAlreadyApprovedException("This merchant is already approved and please complete task their task first.");
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
    public ContractResponse getCurrentContract(ContractRequest request, UserEntity user) {
        ContractEntity contract;
        if(request.getMerchantId()!=null){
            MerchantEntity merchant = merchantRepository.findById(request.getMerchantId()).get();
            contract = contractRepository.findCurrentContractByMerchant(merchant);
        }else{
            contract = contractRepository.findCurrentContract(user.getId());
        }
        if(contract==null){
           return null;
        }
        return new ContractResponse(contract);
    }

    @Override
    public AdminUserContractHistListResponse getContractsList( AdminUserContractHistListRequest request) {
        AdminUserContractHistListResponse response = new AdminUserContractHistListResponse();
        List<AdminUserContractHistoryResponse> contracts = new ArrayList<>();
        List<ContractEntity> dbContracts = contractRepository.findAllByUserUid(request.getUid());

        for(ContractEntity contract : dbContracts){
            contracts.add(new AdminUserContractHistoryResponse(contract));
        }
        response.setContracts(contracts);
        return response;
    }

    @Override
    public AdminUserContractListResponse getUsersContracts(AdminUserContractListRequest request) {
        AdminUserContractListResponse response = new AdminUserContractListResponse();
        List<UserContractInfoResponse> contracts = new ArrayList<>();

        List<ContractEntity> dbContracts = contractRepository.findAllByUserUid(request.getUid());
        for(ContractEntity contract : dbContracts){
            contracts.add(new UserContractInfoResponse(contract));
        }
        response.setContracts(contracts);
        return response;
    }

    @Override
    public AdminUserContractApproveResponse contractApprove(AdminUserContractApproveRequest request) {
        ContractEntity contract = contractRepository.findById(request.getContractId()).get();
        contract.setStatus(CONTRACT_STATUS.APPROVED);
        List<ProductEntity> products = productRepository.findAllByMerchantId(contract.getMerchant().getId());
        Random random = new Random();

        // Shuffle the list using the Random instance
        Collections.shuffle(products, random);

        contract.setTotalTask(products.size());
        int taskNo = 1;
        for(ProductEntity product:products){
            TradeHistoryEntity trade = new TradeHistoryEntity();
            trade.setTaskNumber(taskNo);
            trade.setUser(contract.getUser());
            trade.setState(TRADING_STATE.NOT_START);
            trade.setProduct(product);
            trade.setOrderPrice(product.getPrice());
            tradeHistoryRepository.save(trade);
            taskNo++;
        }
        contract.setCurrentTask(1);
        contract.setFinishedTask(0);
        contract.setTaskComplete(false);
        contractRepository.save(contract);
        return new AdminUserContractApproveResponse(contract);
    }

    @Override
    public AdminUserContractRejectResponse contractReject(AdminUserContractRejectRequest request) {
        ContractEntity contract = contractRepository.findById(request.getContractId()).get();
        contract.setStatus(CONTRACT_STATUS.REJECTED);
        contractRepository.save(contract);
        return new AdminUserContractRejectResponse(contract);
    }
}
