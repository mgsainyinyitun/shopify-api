package com.shopify.api.controller.admin.withdraw;
import com.shopify.api.message.admin.withdraw.*;
import com.shopify.api.message.error.ErrorMessageResponse;
import com.shopify.api.services.withdraw.WithdrawService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/withdraw")
@CrossOrigin(origins="*")
public class AdminUserWithdrawController {
    private final WithdrawService withdrawService;

    public AdminUserWithdrawController(WithdrawService withdrawService) {
        this.withdrawService = withdrawService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> get(@ModelAttribute AdminUserWithdrawListRequest request){
        try{
            AdminUserWithdrawListResponse
            response = withdrawService.adminList(request);
            return  ResponseEntity.ok(response);
        }catch (Exception e){
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PostMapping("/accept/request")
    public ResponseEntity<?> acceptRequest(@RequestBody AdminUserWithdrawApproveRequest request){
        try{
            AdminUserWithdrawApproveResponse
                    response = withdrawService.approveRequest(request);
            return  ResponseEntity.ok(response);
        }catch (Exception e){
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PostMapping("/reject/request")
    public ResponseEntity<?> rejectRequest(@RequestBody AdminUserWithdrawRejectRequest request){
        try{
            AdminUserWithdrawRejectResponse
                    response = withdrawService.rejectRequest(request);
            return  ResponseEntity.ok(response);
        }catch (Exception e){
            ErrorMessageResponse err = new ErrorMessageResponse("ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }
}
