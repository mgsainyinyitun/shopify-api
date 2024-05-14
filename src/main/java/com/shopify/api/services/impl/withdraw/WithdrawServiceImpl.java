package com.shopify.api.services.impl.withdraw;

import com.shopify.api.constant.WITHDRAW;
import com.shopify.api.exceptions.BalanceInsufficientException;
import com.shopify.api.message.admin.withdraw.*;
import com.shopify.api.message.withdraw.UserWithdrawListRequest;
import com.shopify.api.message.withdraw.UserWithdrawListResponse;
import com.shopify.api.message.withdraw.UserWithdrawRequest;
import com.shopify.api.message.withdraw.UserWithdrawResponse;
import com.shopify.api.models.bank.user.UserBankInfoEntity;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.models.withdraw.WithdrawEntity;
import com.shopify.api.repository.bank.UserBankInfoRepository;
import com.shopify.api.repository.user.UserRepository;
import com.shopify.api.repository.withdraw.WithdrawRepository;
import com.shopify.api.services.withdraw.WithdrawService;
import com.shopify.api.utils.NumberFormatUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawServiceImpl implements WithdrawService {
    private final UserRepository userRepository;
    private final UserBankInfoRepository userBankInfoRepository;
    private final WithdrawRepository withdrawRepository;

    public WithdrawServiceImpl(UserRepository userRepository, UserBankInfoRepository userBankInfoRepository, WithdrawRepository withdrawRepository) {
        this.userRepository = userRepository;
        this.userBankInfoRepository = userBankInfoRepository;
        this.withdrawRepository = withdrawRepository;
    }

    @Override
    public UserWithdrawResponse withdraw(UserWithdrawRequest request) {
        UserEntity user = userRepository.findByUid(request.getUid());
        UserBankInfoEntity bankInfo = userBankInfoRepository.findById(request.getUserBankInfoId()).get();

        if(user.getBalance()<request.getAmount()){
            throw new BalanceInsufficientException("User balance is insufficient!");
        }

        WithdrawEntity withdraw = new WithdrawEntity();
        withdraw.setUser(user);
        withdraw.setBank(bankInfo);
        withdraw.setAmount(request.getAmount());
        withdraw.setStatus(WITHDRAW.PENDING);

        withdrawRepository.save(withdraw);
        return new UserWithdrawResponse(withdraw);
    }

    @Override
    public UserWithdrawListResponse all(UserWithdrawListRequest request, UserEntity user) {
        List<WithdrawEntity> dbWithdraws = withdrawRepository.findAllByUser(user);
        List<UserWithdrawResponse> withdraws = new ArrayList<>();

        for(WithdrawEntity withdraw:dbWithdraws){
            withdraws.add(new UserWithdrawResponse(withdraw));
        }
        UserWithdrawListResponse response = new UserWithdrawListResponse();
        response.setWithdraws(withdraws);
        return response;
    }

    @Override
    public AdminUserWithdrawListResponse adminList(AdminUserWithdrawListRequest request) {
        UserEntity user = userRepository.findByUid(request.getUid());
        List<WithdrawEntity> dbWithdraws = withdrawRepository.findAllByUser(user);
        List<UserWithdrawResponse> withdraws = new ArrayList<>();

        for(WithdrawEntity withdraw:dbWithdraws){
            withdraws.add(new UserWithdrawResponse(withdraw));
        }
        AdminUserWithdrawListResponse response = new AdminUserWithdrawListResponse();
        response.setWithdraws(withdraws);
        return response;
    }

    @Override
    public AdminUserWithdrawApproveResponse approveRequest(AdminUserWithdrawApproveRequest request) {
        UserEntity user = userRepository.findByUid(request.getUid());
        WithdrawEntity withdraw = withdrawRepository.findById(request.getWithdrawId()).get();
        withdraw.setStatus(WITHDRAW.ACCEPT);
        user.setBalance(NumberFormatUtils.round(user.getBalance()-withdraw.getAmount(),2));
        withdrawRepository.save(withdraw);
        userRepository.save(user);
        return new AdminUserWithdrawApproveResponse(withdraw);
    }

    @Override
    public AdminUserWithdrawRejectResponse rejectRequest(AdminUserWithdrawRejectRequest request) {
        WithdrawEntity withdraw = withdrawRepository.findById(request.getWithdrawId()).get();
        withdraw.setStatus(WITHDRAW.REJECT);
        withdrawRepository.save(withdraw);
        return new AdminUserWithdrawRejectResponse(withdraw);
    }
}
