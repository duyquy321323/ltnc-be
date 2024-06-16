package com.ltnc.be.domain.employee;

import com.ltnc.be.domain.equipment.Equipment;
import com.ltnc.be.domain.leaveApplication.LeaveApplication;
import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import com.ltnc.be.domain.medicineManagement.MedicineManagement;
import com.ltnc.be.domain.prescription.Prescription;
import com.ltnc.be.domain.roomEmployee.RoomEmployee;
import com.ltnc.be.domain.task.Task;
import com.ltnc.be.domain.user.User;
import com.ltnc.be.domain.user.UserRole;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // đánh dấu đây là 2 entity
@Table(name = "employee") // tạo ra table employee trong csdl
@Getter
@Setter // tạo ra các hàm setter cho thuộc tính
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends User {
  @Enumerated(EnumType.STRING)
  @Column(name = "degree_type")
  private DegreeType degreeType;

  @Enumerated(EnumType.STRING)
  @Column(name = "duty_type")
  private DutyType dutyType;

  @Enumerated(EnumType.STRING)
  @Column(name = "department")
  private Department department;

  @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
  private List<LeaveApplication> leaveApplicationList;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
  private List<RoomEmployee> roomEmployees;

  @ManyToMany(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(
      name = "manage_equipment",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "equipment_id"))
  private List<Equipment> equipments;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
  private List<MedicineManagement> medicineManagementList;

  @ManyToMany(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(
      name = "handle_record",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "record_id"))
  private List<MedicalRecord> medicalRecords;

  @OneToMany(mappedBy = "prescriber", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Prescription> prescriptions;

  // Manager relationship
  @ManyToOne
  @JoinColumn(name = "manager_id")
  private Employee manager;

  // Employees under this manager
  @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Employee> employees;

  // task relationship
  @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Task> tasks;

  public Employee(
      UserRole role,
      String username,
      String email,
      String sex,
      String password,
      String fullName,
      Date dob,
      String phoneNumber,
      String address,
      DegreeType degreeType,
      DutyType dutyType,
      Department department) {
    super(role, username, email, sex, password, fullName, dob, phoneNumber, address);
    this.degreeType = degreeType;
    this.dutyType = dutyType;
    this.department = department;
  }
}
