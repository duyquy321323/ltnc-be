package com.ltnc.be.rest.response;

import com.ltnc.be.domain.patient.PatientType;
import com.ltnc.be.dto.MedicalRecordDTO;
import com.ltnc.be.dto.MedicalTestDTO;
import com.ltnc.be.dto.PatientDTO;
import com.ltnc.be.dto.PatientRoomDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {
    private Long patientId;
    private String name;
    private String BHYT;
    private String phone;
    private String gender;
    private String address;
    private Date dob;
    private PatientType type;
    private List<MedicalRecordDTO> medicalRecords;
    private List<PatientRoomDTO> patientRooms;

    public static PatientResponse toPatientResponse(PatientDTO patientDTO){
        return PatientResponse.builder()
                .patientId(patientDTO.getPatientId())
                .name(patientDTO.getName())
                .BHYT(patientDTO.getBHYT())
                .phone(patientDTO.getPhone())
                .gender(patientDTO.getGender())
                .address(patientDTO.getAddr())
                .dob(patientDTO.getDob())
                .type(patientDTO.getType())
                .medicalRecords(patientDTO.getMedicalRecords())
                .patientRooms(patientDTO.getPatientRooms())
                .build();
    }
}
