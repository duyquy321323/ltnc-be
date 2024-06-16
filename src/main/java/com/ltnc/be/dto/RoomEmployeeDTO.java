package com.ltnc.be.dto;

import com.ltnc.be.domain.roomEmployee.RoomEmployee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomEmployeeDTO {
    private Long roomId;
    private Long employeeId;
    private String employeeName;
    private Long startTime;
    private Long endTime;

    public static RoomEmployeeDTO fromDomain(RoomEmployee roomEmployee){
        return RoomEmployeeDTO.builder()
                .employeeId(roomEmployee.getEmployee().getId())
                .roomId(roomEmployee.getRoom().getId())
                .employeeName(roomEmployee.getEmployee().getFullName())
                .startTime(roomEmployee.getStartTime())
                .endTime(roomEmployee.getEndTime())
                .build();
    }
}
