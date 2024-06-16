package com.ltnc.be.dto;

import com.ltnc.be.domain.patient.Patient;
import com.ltnc.be.domain.patient.PatientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long patientId;
    private String BHYT;
    private String name;
    private String phone;
    private String gender;
    private String addr;
    private Date dob;
    private PatientType type;
    private List<MedicalTestDTO> medicalTests;
    private List<MedicalRecordDTO> medicalRecords;
    private List<PatientRoomDTO> patientRooms;

    public static PatientDTO fromDomain(Patient patient){
        return PatientDTO.builder()
                .patientId(patient.getId())
                .name(patient.getName())
                .BHYT(patient.getBHYT())
                .gender(patient.getGender())
                .phone(patient.getPhone())
                .addr(patient.getAddress())
                .dob(patient.getDob())
                .type(patient.getPatientType())
                .medicalTests(patient.getMedicalTests().stream().map(MedicalTestDTO::fromDomain).collect(Collectors.toList()))
                .medicalRecords(patient.getMedicalRecords().stream().map(MedicalRecordDTO::fromDomain).collect(Collectors.toList()))
                .patientRooms(patient.getPatientRooms().stream().map(PatientRoomDTO::fromDomain).collect(Collectors.toList()))
                .build();

    }
}
