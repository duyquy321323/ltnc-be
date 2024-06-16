package com.ltnc.be.dto;

import com.ltnc.be.domain.prescriptionMedicine.PrescriptionMedicine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionMedicineDTO {
    private Long id;
    private Long medicineId;
    private String medicineName;  // Assuming a simple representation of the medicine
    private Long prescriptionId;  // ID of the associated prescription
    private Integer quantity;
    private String dosage;

    // Static method to transform from domain model to DTO
    public static PrescriptionMedicineDTO fromDomain(PrescriptionMedicine prescriptionMedicine) {
        return PrescriptionMedicineDTO.builder()
                .id(prescriptionMedicine.getId())
                .medicineId(prescriptionMedicine.getMedicine().getId())
                .medicineName(prescriptionMedicine.getMedicine().getName())  // Simplified to name; adjust as needed
                .prescriptionId(prescriptionMedicine.getPrescription().getId())
                .quantity(prescriptionMedicine.getQuantity())
                .dosage(prescriptionMedicine.getDosage())
                .build();
    }
}
