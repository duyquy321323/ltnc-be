package com.ltnc.be.dto;

import com.ltnc.be.domain.leaveApplication.LeaveApplication;
import com.ltnc.be.domain.leaveApplication.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class LeaveApplicationDTO {
    private Long id;
    private String reason;
    private Date time_off;
    private Status status;
    private Long employeeId;
    private String employeeName;

    public static LeaveApplicationDTO fromDomain(LeaveApplication leaveApplication){
        return LeaveApplicationDTO.builder()
                .id(leaveApplication.getId())
                .reason(leaveApplication.getReason())
                .time_off(leaveApplication.getTimeOff())
                .status(leaveApplication.getStatus())
                .employeeId(leaveApplication.getEmployee().getId())
                .employeeName(leaveApplication.getEmployee().getFullName())
                .build();
    }
}
