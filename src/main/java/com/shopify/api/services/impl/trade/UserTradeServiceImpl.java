package com.shopify.api.services.impl.trade;

import com.shopify.api.constant.CONTRACT_STATUS;
import com.shopify.api.constant.TRADING_STATE;
import com.shopify.api.exceptions.BalanceInsufficientException;
import com.shopify.api.exceptions.UserMembershipInsufficientException;
import com.shopify.api.message.trade.*;
import com.shopify.api.models.contract.ContractEntity;
import com.shopify.api.models.product.ProductEntity;
import com.shopify.api.models.trade.TradeHistoryEntity;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.repository.TradeHistoryRepository;
import com.shopify.api.repository.contract.ContractRepository;
import com.shopify.api.repository.product.ProductRepository;
import com.shopify.api.repository.user.UserRepository;
import com.shopify.api.services.trade.UserTradeService;
import com.shopify.api.utils.NumberFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserTradeServiceImpl implements UserTradeService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TradeHistoryRepository tradeHistoryRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserTradeResponse trade(UserTradeRequest request, UserEntity user) {
        ProductEntity product = productRepository.findById(request.getProductId()).get();
        int blCmp = user.getBalance().compareTo(product.getPrice());
        if(blCmp < 0){
            throw new BalanceInsufficientException("User balance is insufficient for trade this product!");
        }else if(user.getMembership()<product.getRating()){
            throw new UserMembershipInsufficientException("User membership rating is insufficient for trade this product!");
        }
//        TradeHistoryEntity tradeHistory = new TradeHistoryEntity();
//        tradeHistory.setUser(user);
//        tradeHistory.setProduct(product);
//        tradeHistory.setTaskNumber(request.getTaskNumber());
//        tradeHistory.setState(TRADING_STATE.PENDING);
//        tradeHistory.setOrderPrice(product.getPrice());

//          TradeHistoryEntity tradeHistory = tradeHistoryRepository.findCurrentTrades(user.getId(),request.getProductId()).get(0);
        TradeHistoryEntity tradeHistory = tradeHistoryRepository.findById(request.getTradeId()).get();

//        ContractEntity contract = contractRepository.findByStatusIsApproveForUser(user.getId());
//        contract.setCurrentTask(request.getTaskNumber() + 1);

        tradeHistory.setState(TRADING_STATE.PENDING);

        tradeHistoryRepository.save(tradeHistory);
        return new UserTradeResponse(tradeHistory);
    }

    @Override
    public UserTradeFinishedResponse tradeFinished(UserTradeFinishedRequest request, UserEntity user) {
        TradeHistoryEntity trade = tradeHistoryRepository.findById(request.getTradeId()).get();
        ContractEntity contract = contractRepository.findByStatusIsApproveForUser(user.getId());

        trade.setState(TRADING_STATE.FINISHED);
        contract.setCurrentTask(contract.getCurrentTask() + 1);
        contract.setFinishedTask(contract.getFinishedTask() + 1);

        if(Objects.equals(contract.getFinishedTask(), contract.getTotalTask())){
            contract.setTaskComplete(true);
            contract.setStatus(CONTRACT_STATUS.FINISHED);
        }

        tradeHistoryRepository.save(trade);
        contractRepository.save(contract);

        Double balance = user.getBalance();
        Double commission = (trade.getOrderPrice() * trade.getProduct().getCommission())/100;
        user.setBalance(NumberFormatUtils.round( balance+commission,3));
        user.setRevenue(NumberFormatUtils.round(user.getRevenue()+commission,3))  ;
        userRepository.save(user);

        return new UserTradeFinishedResponse(trade);
    }

    @Override
    public UserTradeLogListResponse tradeLogs(UserTradeLogRequest request, UserEntity user) {
        List<TradeHistoryEntity> dbTrades = tradeHistoryRepository.findAllByUserId(user.getId());
        UserTradeLogListResponse response = new UserTradeLogListResponse();
        List<UserTradeLogResponse> trades = new ArrayList<>();
        for(TradeHistoryEntity d:dbTrades){
            trades.add(new UserTradeLogResponse(d));
        }
        response.setTradeLogs(trades);
        return response;
    }
}