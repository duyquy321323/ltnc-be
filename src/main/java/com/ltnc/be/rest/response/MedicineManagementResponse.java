package com.ltnc.be.rest.response;

import com.ltnc.be.dto.MedicineManagementDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MedicineManagementResponse {
    private Long id;
    private Long medicineId;
    private String medicineName;
    private Long inputQuantity;
    private Long employeeId;
    private String employeeName;
    private Long expiredDate;
    private String code;

    public static MedicineManagementResponse toMedicineManagementResponse(MedicineManagementDTO medicineManagementDTO) {
        return MedicineManagementResponse.builder()
                .id(medicineManagementDTO.getId())
                .medicineId(medicineManagementDTO.getMedicineId())
                .medicineName(medicineManagementDTO.getMedicineName())
                .inputQuantity(medicineManagementDTO.getInputQuantity())
                .employeeId(medicineManagementDTO.getEmployeeId())
                .employeeName(medicineManagementDTO.getEmployeeName())
                .expiredDate(medicineManagementDTO.getExpiredDate())
                .code(medicineManagementDTO.getCode())
                .build();
    }
}
