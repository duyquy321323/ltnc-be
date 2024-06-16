package com.ltnc.be.rest.response;

import com.ltnc.be.domain.employee.DegreeType;
import com.ltnc.be.domain.employee.Department;
import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.dto.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Long employeeId;
    private String employeeName;
    private DutyType dutyType;
    private DegreeType degreeType;
    private String address;
    private String phoneNumber;
    private String gender;
    private Department department;
    private List<TaskDTO> tasks;
    private List<EquipmentDTO> equipments;
    private List<PrescriptionDTO> prescriptions;
    private List<MedicalRecordDTO> medicalRecords;
    private List<MedicineManagementDTO> medicineManagements;
    private List<RoomEmployeeDTO> roomEmployees;

    public static EmployeeResponse toEmployeeResponse(com.ltnc.be.dto.EmployeeDTO employeeDTO) {
        return EmployeeResponse.builder()
                .employeeId(employeeDTO.getEmployeeId())
                .employeeName(employeeDTO.getEmployeeName())
                .dutyType(employeeDTO.getDutyType())
                .degreeType(employeeDTO.getDegreeType())
                .address(employeeDTO.getAddress())
                .phoneNumber(employeeDTO.getPhoneNumber())
                .gender(employeeDTO.getGender())
                .department(employeeDTO.getDepartment())
                .roomEmployees(employeeDTO.getRoomEmployees())
                .tasks(employeeDTO.getTasks())
                .equipments(employeeDTO.getEquipments())
                .prescriptions(employeeDTO.getPrescriptions())
                .medicalRecords(employeeDTO.getMedicalRecords())
                .medicineManagements(employeeDTO.getMedicineManagements())
                .build();
    }
}
