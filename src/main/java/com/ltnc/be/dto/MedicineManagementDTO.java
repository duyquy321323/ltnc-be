package com.ltnc.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineManagementDTO {
    private Long id;
    private Long medicineId;
    private String medicineName;
    private Long inputQuantity;
    private Long employeeId;
    private String employeeName;
    private Long expiredDate;
    private String code;

    public static MedicineManagementDTO fromDomain(com.ltnc.be.domain.medicineManagement.MedicineManagement management) {
        return MedicineManagementDTO.builder()
                .id(management.getId())
                .medicineId(management.getMedicine().getId())
                .medicineName(management.getMedicine().getName())  // You need to have a getName() method in Medicine
                .inputQuantity(management.getInput_quantity())
                .employeeId(management.getEmployee().getId())
                .employeeName(management.getEmployee().getFullName())  // Assuming Employee has getFullName()
                .expiredDate(management.getExpiredDate())
                .code(management.getCode())
                .build();
    }
}
