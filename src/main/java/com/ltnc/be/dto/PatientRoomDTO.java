package com.ltnc.be.dto;

import com.ltnc.be.domain.patientRoom.PatientRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientRoomDTO {
    private Long roomId;
    private Long startTime;
    private Long endTime;
    private int bedNumber;
    private Long patientId;
    private String patientName;

    public static PatientRoomDTO fromDomain(PatientRoom patientRoom){
        return PatientRoomDTO.builder()
                .roomId(patientRoom.getRoom().getId())
                .bedNumber(patientRoom.getBedNumber())
                .startTime(patientRoom.getStartTime())
                .endTime(patientRoom.getEndTime())
                .patientId(patientRoom.getPatient().getId())
                .patientName(patientRoom.getPatient().getName())
                .build();
    }
}
