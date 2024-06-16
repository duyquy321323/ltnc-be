package com.ltnc.be.rest.controller;

import com.ltnc.be.port.facade.AccountFacade;
import com.ltnc.be.rest.request.LoginRequest;
import com.ltnc.be.rest.request.UpsertEmployeeRequest;
import com.ltnc.be.rest.response.BaseResponse;
import com.ltnc.be.rest.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountController {
  private final AccountFacade accountFacade;

  @PostMapping("/login")
  @Operation(tags = "Account APIs") // đánh dấu đây là 1 thành phần của tag Account APIs swagger
  @ResponseStatus(HttpStatus.OK)
  BaseResponse<LoginResponse> login(
      @RequestBody LoginRequest request) { // chuyển json thành đối tượng LoginRequest
    return BaseResponse.of(accountFacade.login(request));
  }

  @PostMapping("/create-employee")
  @Operation(tags = "Account APIs")
  @ResponseStatus(HttpStatus.CREATED)
  BaseResponse<Void> signUp(@RequestBody UpsertEmployeeRequest request) {
    accountFacade.createEmployee(request);
    return BaseResponse.empty();
  }
}
