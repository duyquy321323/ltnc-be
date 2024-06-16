package com.ltnc.be.rest.response;

import com.ltnc.be.domain.task.TaskStatus;
import com.ltnc.be.dto.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private Long taskId;
    private Date startTime;
    private Date endTime;
    private String description;
    private TaskStatus status;
    private Long employeeId;
    private String employeeName;

    public static TaskResponse toTaskResponse(TaskDTO taskDTO){
        return TaskResponse.builder()
                .taskId(taskDTO.getTaskId())
                .startTime(taskDTO.getStartTime())
                .endTime(taskDTO.getEndTime())
                .description(taskDTO.getDescription())
                .status(taskDTO.getStatus())
                .employeeId(taskDTO.getEmployeeId())
                .employeeName(taskDTO.getEmployeeName())
                .build();
    }
}
