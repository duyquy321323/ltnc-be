package com.ltnc.be.rest.controller;

import com.ltnc.be.annotation.IsAuthenticated;
import com.ltnc.be.annotation.IsAuthorizedAsAdmin;
import com.ltnc.be.annotation.IsAuthorizedAsMember;
import com.ltnc.be.domain.employee.Department;
import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.port.facade.EmployeeFacade;
import com.ltnc.be.rest.request.UpsertEmployeeRequest;
import com.ltnc.be.rest.response.BaseResponse;
import com.ltnc.be.rest.response.EmployeeResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    EmployeeFacade employeeFacade;
    @GetMapping("/")
    @Operation(tags = "Employee APIs")
    @ResponseStatus(HttpStatus.OK)
    //@IsAuthorizedAsAdmin
    public BaseResponse<List<EmployeeResponse>> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo,
                                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                                             @RequestParam(defaultValue = "id") String sortBy,
                                                             @RequestParam(defaultValue = "0") Boolean searchFlag,
                                                             @RequestParam(required = false) String fullName,
                                                             @RequestParam(required = false) DutyType dutyType,
                                                             @RequestParam(required = false) Department department) {
        if(searchFlag == Boolean.TRUE){
            return BaseResponse.of(employeeFacade.searchEmployees(fullName, dutyType, department, pageNo, pageSize, sortBy));
        }
        return BaseResponse.of(employeeFacade.getAllEmployees(pageNo, pageSize, sortBy));
    }

    @GetMapping("/{managerId}/")
    @Operation(tags = "Employee APIs")
    @ResponseStatus(HttpStatus.OK)
    //@IsAuthenticated
    public BaseResponse<List<EmployeeResponse>> getAllEmployeesManagedByEmployee(@PathVariable Long managerId,
                                                             @RequestParam(defaultValue = "0") Integer pageNo,
                                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                                             @RequestParam(defaultValue = "id") String sortBy,
                                                             @RequestParam(defaultValue = "0") Boolean searchFlag,
                                                             @RequestParam(required = false) String fullName,
                                                             @RequestParam(required = false) DutyType dutyType,
                                                             @RequestParam(required = false) Department department) {

        if(searchFlag == Boolean.TRUE){
            return BaseResponse.of(employeeFacade.searchAllEmployeesManagedByEmployee(managerId, fullName, dutyType, department, pageNo, pageSize, sortBy));
        }
        return BaseResponse.of(employeeFacade.getAllEmployeesManagedByEmployee(managerId, pageNo, pageSize, sortBy));
    }

    @DeleteMapping("/{employeeId}")
    @Operation(tags = "Employee APIs")
    @ResponseStatus(HttpStatus.OK)
    //@IsAuthorizedAsAdmin
    public BaseResponse<Void> deleteEmployeeById(@PathVariable Long employeeId){
        employeeFacade.deleteEmployeeById(employeeId);
        return BaseResponse.empty();
    }

    @PatchMapping("/{employeeId}")
    @Operation(tags = "Employee APIs")
    @ResponseStatus(HttpStatus.OK)
    //@IsAuthorizedAsAdmin
    public BaseResponse<Void> UpdateEmployee(@PathVariable Long employeeId, @RequestBody UpsertEmployeeRequest request){
        employeeFacade.UpdateEmployee(employeeId,request);
        return BaseResponse.empty();
    }

    @GetMapping("/{employeeId}")
    @Operation(tags = "Employee APIs")
    @ResponseStatus(HttpStatus.OK)
    //@IsAuthorizedAsAdmin
    public BaseResponse<EmployeeResponse> searchEmployeeById(@PathVariable Long employeeId){
        return BaseResponse.of(employeeFacade.getEmployeeById(employeeId));
    }
}
