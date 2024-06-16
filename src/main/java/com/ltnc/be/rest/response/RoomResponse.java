package com.ltnc.be.rest.response;

import com.ltnc.be.dto.PatientRoomDTO;
import com.ltnc.be.dto.RoomDTO;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomResponse {
    private Long roomId;
    private int capacity;
    private String roomNumber;
    private List<PatientRoomDTO> patients;

    public static RoomResponse toRoomResponse(RoomDTO roomDTO){
        return RoomResponse.builder()
                .roomId(roomDTO.getRoomId())
                .capacity(roomDTO.getCapacity())
                .roomNumber(roomDTO.getRoomNumber())
                .patients(roomDTO.getPatientRooms())
                .build();
    }
}
