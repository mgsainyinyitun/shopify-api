package com.shopify.api.controller.trade;

import com.shopify.api.message.error.ErrorMessageResponse;
import com.shopify.api.message.trade.UserTradeFinishedRequest;
import com.shopify.api.message.trade.UserTradeFinishedResponse;
import com.shopify.api.message.trade.UserTradeRequest;
import com.shopify.api.message.trade.UserTradeResponse;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.security.UserUtils;
import com.shopify.api.services.trade.UserTradeService;
import com.shopify.api.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trade")
@CrossOrigin(origins="*")
public class UserTradeController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserTradeService userTradeService;

    @PostMapping("/start-trade")
    public ResponseEntity<?> trade(@RequestBody UserTradeRequest request) {
        UserTradeResponse response;
        try {
            response = userTradeService.trade(request,getUser());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PostMapping("/trade-finish")
    public ResponseEntity<?> tradeFinish(@RequestBody UserTradeFinishedRequest request) {
        UserTradeFinishedResponse response;
        try {
            response = userTradeService.tradeFinished(request,getUser());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    UserEntity getUser(){
        UserEntity user;
        String username = UserUtils.getLoginUserName();
        if(username!=null){
            user = userService.getUserByUsername(username);
        }else{
            user = null;
        }
        return user;
    }

}
