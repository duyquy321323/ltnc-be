package com.ltnc.be.adapter.facade;

import com.ltnc.be.domain.employee.Department;
import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.exception.EntityNotFoundException;
import com.ltnc.be.dto.EmployeeDTO;
import com.ltnc.be.port.facade.EmployeeFacade;
import com.ltnc.be.port.repository.EmployeeRepository;
import com.ltnc.be.rest.request.UpsertEmployeeRequest;
import com.ltnc.be.rest.response.EmployeeResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeFacadeImpl implements EmployeeFacade {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponse> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        var employees = employeeRepository.findAll(paging);
        if(employees.hasContent()){
            var employeesDto = employees.getContent().stream().map(EmployeeDTO::fromDomain).toList();
            return employeesDto.stream().map(EmployeeResponse::toEmployeeResponse).toList();
        }
        else{
            return new ArrayList<EmployeeResponse>();
        }
    }
    @Override
    public List<EmployeeResponse> searchEmployees(String name, DutyType dutyType, Department department, Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        var employees = employeeRepository.findEmployeesBySearchCriteria(name, dutyType,department, paging);
        if(employees.hasContent()){
            var employeesDto = employees.getContent().stream().map(EmployeeDTO::fromDomain).toList();
            return employeesDto.stream().map(EmployeeResponse::toEmployeeResponse).toList();
        }
        else{
            return new ArrayList<EmployeeResponse>();
        }
    }

    @Override
    public List<EmployeeResponse> getAllEmployeesManagedByEmployee(Long managerId, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Employee> managedEmployees = employeeRepository.findByManagerId(managerId, paging);

        if (managedEmployees.hasContent()) {
            return managedEmployees.getContent().stream()
                    .map(EmployeeDTO::fromDomain) // Assuming you have a method to convert from domain to DTO
                    .map(EmployeeResponse::toEmployeeResponse) // Convert DTOs to responses
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<EmployeeResponse> searchAllEmployeesManagedByEmployee(Long managerId, String name, DutyType dutyType, Department department, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Employee> employees = employeeRepository.findAllByManagerAndCriteria(managerId, name, dutyType, department, paging);

        if (employees.hasContent()) {
            return employees.getContent().stream()
                    .map(EmployeeDTO::fromDomain)// Convert Employee to EmployeeDTO
                    .map(EmployeeResponse::toEmployeeResponse) // Convert EmployeeDTO to EmployeeResponse
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    @SneakyThrows
    public void deleteEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findEmployeeById(employeeId);
        if(employee.isPresent()){
            employeeRepository.deleteById(employeeId);
        }else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    @SneakyThrows
    public void UpdateEmployee(Long employeeId, UpsertEmployeeRequest request) {
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeById(employeeId);
        if(optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (request.getFullName() != null) {
                employee.setFullName(request.getFullName());
            }
            if (request.getAddress() != null) {
                employee.setAddress(request.getAddress());
            }
            if (request.getPhone() != null) {
                employee.setPhoneNumber(request.getPhone());
            }
            if (request.getDegreeType() != null) {
                employee.setDegreeType(request.getDegreeType());
            }
            if (request.getDutyType() != null) {
                employee.setDutyType(request.getDutyType());
            }
            if (request.getDepartment() != null) {
                employee.setDepartment(request.getDepartment());
            }
            employeeRepository.save(employee);
        }else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    @SneakyThrows
    public EmployeeResponse getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeById(id);

        if(optionalEmployee.isPresent()){
            EmployeeDTO employeeDTO = EmployeeDTO.fromDomain(optionalEmployee.get());
            return EmployeeResponse.toEmployeeResponse(employeeDTO);
        }else {
            throw new EntityNotFoundException();
        }
    }
}
