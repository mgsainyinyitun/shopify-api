package com.shopify.api.controller.bank;


import com.shopify.api.message.bank.*;
import com.shopify.api.message.error.ErrorMessageResponse;
import com.shopify.api.services.bank.UserBankInfoService;
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
            response = userBankInfoService.getAllUserBankInfos(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }
}
