package com.ltnc.be.dto;

import com.ltnc.be.domain.medicalTest.MedicalTest;
import com.ltnc.be.domain.medicalTest.TestType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalTestDTO {
    private Long testId;
    private TestType testType;
    private String result;
    private Long patientId;
    private String patientName;
    private Long recordId;

    public static MedicalTestDTO fromDomain(MedicalTest medicalTest){
        return MedicalTestDTO.builder()
                .testId(medicalTest.getId())
                .testType(medicalTest.getTestType())
                .result(medicalTest.getResult())
                .patientId(medicalTest.getPatient().getId())
                .patientName(medicalTest.getPatient().getName())
                .recordId(medicalTest.getMedicalRecord().getId())
                .build();
    }
}
