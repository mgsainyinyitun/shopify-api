package com.shopify.api.controller.contract;

import com.shopify.api.message.admin.user.AdminUserContractHistListRequest;
import com.shopify.api.message.admin.user.AdminUserContractHistListResponse;
import com.shopify.api.message.contract.*;
import com.shopify.api.message.error.ErrorMessageResponse;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.security.UserUtils;
import com.shopify.api.services.contract.ContractService;
import com.shopify.api.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins="*")
public class ContractController {

    @Autowired
    ContractService contractService;

    @Autowired
    UserService userService;

    @PostMapping("/sign")
    public ResponseEntity<?> signContract(@RequestBody ContractSignRequest request){
        ContractSignResponse response;
        try {
            UserEntity user = getUser();
            response = contractService.signContract(request,user);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @GetMapping("/current")
    public ResponseEntity<?> getContract(@ModelAttribute ContractRequest request){
        try{
            ContractResponse response;
            response = contractService.getCurrentContract(request,getUser());
            return  ResponseEntity.ok(response);
        }catch (Exception e){
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllContracts(@ModelAttribute ContractListRequest request){
        ContractListResponse response;
        try{
            UserEntity user = getUser();
            response = contractService.listContracts(request,user);
            return  ResponseEntity.ok(response);
        }catch (Exception e){
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @GetMapping("/trade-user/contracts")
    public ResponseEntity<?> userContracts(@ModelAttribute AdminUserContractHistListRequest request){
        try {
            AdminUserContractHistListResponse res = contractService.getContractsList(request);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
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
