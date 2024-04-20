package com.shopify.api.controller.contract;


import com.shopify.api.message.contract.ContractListRequest;
import com.shopify.api.message.contract.ContractListResponse;
import com.shopify.api.message.contract.ContractSignRequest;
import com.shopify.api.message.contract.ContractSignResponse;
import com.shopify.api.message.error.ErrorMessageResponse;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.services.contract.ContractService;
import com.shopify.api.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "http://localhost:3000")
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
        } catch (Exception e) {
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

    UserEntity getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user;
        if (authentication != null) {
            System.out.println(authentication.getName());
            user = userService.getUserByUsername(authentication.getName());
        } else {
            user = null;
        }
        return user;
    }
}