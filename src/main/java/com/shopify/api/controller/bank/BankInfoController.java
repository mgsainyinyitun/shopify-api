package com.shopify.api.controller.bank;

import com.shopify.api.message.bank.BankInfoListRequest;
import com.shopify.api.message.bank.BankInfoListResponse;
import com.shopify.api.message.bank.UserBankInfoListRequest;
import com.shopify.api.message.error.ErrorMessageResponse;
import com.shopify.api.services.bank.BankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin(origins = "http://localhost:3000")
public class BankInfoController {

    @Autowired
    BankInfoService bankInfoService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@ModelAttribute BankInfoListRequest request) {
        BankInfoListResponse response;
        try {
            response = bankInfoService.getBankInfoList(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }
}
