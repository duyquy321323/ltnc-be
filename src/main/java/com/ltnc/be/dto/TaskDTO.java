package com.ltnc.be.dto;

import com.ltnc.be.domain.task.Task;
import com.ltnc.be.domain.task.TaskStatus;
import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long taskId;
    private Date startTime;
    private Date endTime;
    private String description;
    private TaskStatus status;
    private Long employeeId;
    private String employeeName;

    public static TaskDTO fromDomain(Task task){
        return TaskDTO.builder()
                .taskId(task.getId())
                .startTime(task.getStartTime())
                .endTime(task.getEndTime())
                .status(task.getStatus())
                .description(task.getDescription())
                .employeeId(task.getEmployee().getId())
                .employeeName(task.getEmployee().getFullName())
                .build();
    }
}
