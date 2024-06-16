package com.ltnc.be.port.repository;

import com.ltnc.be.domain.employee.Department;
import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.domain.employee.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

  Optional<Employee> findEmployeeById(long id);

  @Query(
      "SELECT e FROM Employee e WHERE (:name IS NULL OR e.fullName LIKE %:name%) "
          + "AND (:dutyType IS NULL OR e.dutyType = :dutyType)"
          + "AND (:department IS NULL OR e.department = :department)")
  Page<Employee> findEmployeesBySearchCriteria(
      String name, DutyType dutyType, Department department, Pageable pageable);

  @Query(
      "SELECT e FROM Employee e WHERE (:managerId IS NULL OR e.manager.id = :managerId) "
          + "AND (:name IS NULL OR e.fullName LIKE %:name%) "
          + "AND (:dutyType IS NULL OR e.dutyType = :dutyType)"
          + "AND (:department IS NULL OR e.department = :department)")
  Page<Employee> findAllByManagerAndCriteria(
      Long managerId, String name, DutyType dutyType, Department department, Pageable pageable);

  Page<Employee> findByManagerId(Long managerId, Pageable pageable);

  void deleteById(Long id);

  void save(Employee employee);

  Optional<List<Employee>> findAllByDutyType(DutyType dutyType);
}
