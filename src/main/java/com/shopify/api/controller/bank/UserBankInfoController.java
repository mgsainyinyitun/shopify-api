package com.shopify.api.controller.bank;


import com.shopify.api.message.bank.*;
import com.shopify.api.message.error.ErrorMessageResponse;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.security.UserUtils;
import com.shopify.api.services.bank.UserBankInfoService;
import com.shopify.api.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/bank")
@CrossOrigin(origins="*")
public class UserBankInfoController {

    @Autowired
    UserBankInfoService userBankInfoService;

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserBankInfoCreateRequest request) {
        UserBankInfoCreateResponse response;
        try {
            response = userBankInfoService.createUserBankInfo(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@ModelAttribute UserBankInfoListRequest request) {
        UserBankInfoListResponse response;
        try {
            response = userBankInfoService.getAllUserBankInfos(request,getUser());
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
