package com.ltnc.be.rest.response;

import com.ltnc.be.domain.leaveApplication.Status;
import com.ltnc.be.dto.LeaveApplicationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class LeaveAppResponse {
    private Long id;
    private String reason;
    private Status status;
    private Date timeOff;
    private Long employeeId;
    private String employeeName;

    public static LeaveAppResponse toLeaveAppResponse(LeaveApplicationDTO leaveApplicationDTO){
        return LeaveAppResponse.builder()
                .id(leaveApplicationDTO.getId())
                .reason(leaveApplicationDTO.getReason())
                .timeOff(leaveApplicationDTO.getTime_off())
                .status(leaveApplicationDTO.getStatus())
                .employeeId(leaveApplicationDTO.getEmployeeId())
                .employeeName(leaveApplicationDTO.getEmployeeName())
                .build();
    }
}
