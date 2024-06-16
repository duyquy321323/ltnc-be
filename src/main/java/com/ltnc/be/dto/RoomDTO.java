package com.ltnc.be.dto;

import com.ltnc.be.domain.room.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
public class RoomDTO {
    private Long roomId;
    private int capacity;
    private String roomNumber;
    private List<PatientRoomDTO> patientRooms;
    private List<RoomEmployeeDTO> roomEmployees;

    public static RoomDTO fromDomain(Room room){
        return RoomDTO.builder()
                .roomId(room.getId())
                .capacity(room.getRoomCapacity())
                .roomNumber(room.getRoomNumber())
                .patientRooms(room.getPatientRooms().stream().map(PatientRoomDTO::fromDomain).collect(Collectors.toList()))
                .roomEmployees(room.getRoomEmployees().stream().map(RoomEmployeeDTO::fromDomain).collect(Collectors.toList()))
                .build();
    }
}
