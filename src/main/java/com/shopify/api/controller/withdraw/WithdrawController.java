package com.shopify.api.controller.withdraw;

import com.shopify.api.message.error.ErrorMessageResponse;
import com.shopify.api.message.withdraw.UserWithdrawListRequest;
import com.shopify.api.message.withdraw.UserWithdrawListResponse;
import com.shopify.api.message.withdraw.UserWithdrawRequest;
import com.shopify.api.message.withdraw.UserWithdrawResponse;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.security.UserUtils;
import com.shopify.api.services.user.UserService;
import com.shopify.api.services.withdraw.WithdrawService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/withdraw")
@CrossOrigin(origins="*")
public class WithdrawController {
    private final WithdrawService withdrawService;
    private final UserService userService;

    public WithdrawController(WithdrawService withdrawService, UserService userService) {
        this.withdrawService = withdrawService;
        this.userService = userService;
    }

    @PostMapping("/request")
    public ResponseEntity<?> withdraw(@RequestBody UserWithdrawRequest request){
        UserWithdrawResponse response;
        try {
            response = withdrawService.withdraw(request);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@ModelAttribute UserWithdrawListRequest request){
        try {
            UserEntity user = getUser();
            UserWithdrawListResponse response = withdrawService.all(request,user);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
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
