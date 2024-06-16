package com.ltnc.be.rest.response;

import com.ltnc.be.dto.MedicalRecordDTO;
import com.ltnc.be.dto.MedicalTestDTO;
import com.ltnc.be.dto.PrescriptionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordResponse {
    private Long id;
    private String diagnostic;
    private Date hospitalizedTime;
    private Date leaveTime;
    private Long patientId;
    private String patientName;
    private List<Long> employeeIds;  // Simplifying employee details to IDs
    private List<MedicalTestDTO> medicalTests;
    private List<PrescriptionDTO> prescriptions;  // IDs of related prescriptions

    public static RecordResponse toResponse(MedicalRecordDTO medicalRecordDTO){
        return RecordResponse.builder()
                .id(medicalRecordDTO.getId())
                .diagnostic(medicalRecordDTO.getDiagnostic())
                .hospitalizedTime(medicalRecordDTO.getHospitalizedTime())
                .leaveTime(medicalRecordDTO.getLeaveTime())
                .patientId(medicalRecordDTO.getPatientId())
                .patientName(medicalRecordDTO.getPatientName())
                .employeeIds(medicalRecordDTO.getEmployeeIds())
                .medicalTests(medicalRecordDTO.getMedicalTests())
                .prescriptions(medicalRecordDTO.getPrescriptions())
                .build();
    }
}
