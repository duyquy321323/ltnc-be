package com.ltnc.be.rest.controller;

import com.ltnc.be.port.facade.LeaveAppFacade;
import com.ltnc.be.rest.request.ResponseLeaveAppRequest;
import com.ltnc.be.rest.request.UpsertLeaveAppRequest;
import com.ltnc.be.rest.response.BaseResponse;
import com.ltnc.be.rest.response.LeaveAppResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leave-apps")
@RequiredArgsConstructor
public class LeaveApplicationController {
    private final LeaveAppFacade leaveAppFacade;

    @GetMapping("/")
    @Operation(tags = "LeaveApp APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<LeaveAppResponse>> getAllLeaveApp(){
        return BaseResponse.of(leaveAppFacade.getAllLeaveApp());
    }

    @PatchMapping("/{leaveAppId}")
    @Operation(tags = "LeaveApp APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Void> responseLeaveApp(@PathVariable Long leaveAppId, @RequestBody ResponseLeaveAppRequest request){
        leaveAppFacade.responseLeaveApp(leaveAppId,request);
        return BaseResponse.empty();
    }

    @DeleteMapping("/{leaveAppId}")
    @Operation(tags = "LeaveApp APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Void> deleteLeaveApp(@PathVariable Long leaveAppId){
        leaveAppFacade.deleteLeaveApp(leaveAppId);
        return BaseResponse.empty();
    }

    @PostMapping("/create-leaveApp")
    @Operation(tags = "LeaveApp APIs")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<Void> createLeaveApp(@RequestBody UpsertLeaveAppRequest request){
        leaveAppFacade.sendLeaveApp(request);
        return BaseResponse.empty();
    }
}
