package com.shopify.api.controller.admin.contract;
import com.shopify.api.message.admin.contract.*;
import com.shopify.api.message.error.ErrorMessageResponse;
import com.shopify.api.services.contract.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/contracts")
@CrossOrigin(origins="*")
public class AdminContractController {

    @Autowired
    ContractService contractService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllContracts(@ModelAttribute AdminUserContractListRequest request) {
        try{
            AdminUserContractListResponse response = contractService.getUsersContracts(request);
            return ResponseEntity.ok(response);

        }catch (Exception e){
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PostMapping("/approve/request")
    public  ResponseEntity<?> approveContract(@RequestBody AdminUserContractApproveRequest request) {
        try{
            AdminUserContractApproveResponse response = contractService.contractApprove(request);
            return  ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PostMapping("/reject/request")
    public  ResponseEntity<?> rejectContract(@RequestBody AdminUserContractRejectRequest request) {
        try{
            AdminUserContractRejectResponse response = contractService.contractReject(request);
            return  ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

}
