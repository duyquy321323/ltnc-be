package com.ltnc.be.rest.request;

import com.ltnc.be.domain.task.TaskStatus;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertTaskRequest extends BaseRequest {
  private Date startTime;
  private Date endTime;
  private String description;
  private TaskStatus status;
  private Long employeeId;
}
