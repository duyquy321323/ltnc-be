package com.ltnc.be.rest.request;

import com.ltnc.be.domain.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertTaskRequest extends BaseRequest{
    private Date startTime;
    private Date endTime;
    private String description;
    private TaskStatus status;
    private Long employeeId;
}
