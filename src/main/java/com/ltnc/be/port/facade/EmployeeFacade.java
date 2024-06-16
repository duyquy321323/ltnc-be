package com.ltnc.be.port.facade;

import com.ltnc.be.domain.employee.Department;
import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.rest.request.UpsertEmployeeRequest;
import com.ltnc.be.rest.response.EmployeeResponse;
import java.util.List;

public interface EmployeeFacade {
  List<EmployeeResponse> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy);

  List<EmployeeResponse> searchEmployees(
      String name,
      DutyType dutyType,
      Department department,
      Integer pageNo,
      Integer pageSize,
      String sortBy);

  List<EmployeeResponse> getAllEmployeesManagedByEmployee(
      Long employeeId, Integer pageNo, Integer pageSize, String sortBy);

  List<EmployeeResponse> searchAllEmployeesManagedByEmployee(
      Long employeeId,
      String name,
      DutyType dutyType,
      Department department,
      Integer pageNo,
      Integer pageSize,
      String sortBy);

  void deleteEmployeeById(Long employeeId);

  void UpdateEmployee(Long employeeId, UpsertEmployeeRequest request);

  EmployeeResponse getEmployeeById(Long id);
}
