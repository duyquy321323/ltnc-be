package com.ltnc.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDTO {
    private Long id;
    private String userManual;
    private Long prescriberId;
    private String prescriberName;
    private Long patientId;
    private String patientName;
    private Long medicalRecordId;
    private List<PrescriptionMedicineDTO> prescriptionMedicines;  // Use DTO type for PrescriptionMedicine

    public static PrescriptionDTO fromDomain(com.ltnc.be.domain.prescription.Prescription prescription) {
        return PrescriptionDTO.builder()
                .id(prescription.getId())
                .userManual(prescription.getUserManual())
                .prescriberId(prescription.getPrescriber().getId())
                .prescriberName(prescription.getPrescriber().getFullName())
                .patientId(prescription.getPatient().getId())
                .patientName(prescription.getPatient().getName())
                .medicalRecordId(prescription.getMedicalRecord() != null ? prescription.getMedicalRecord().getId() : null)
                .prescriptionMedicines(prescription.getPrescriptionMedicines().stream()
                        .map(PrescriptionMedicineDTO::fromDomain)
                        .collect(Collectors.toList()))
                .build();
    }
}
