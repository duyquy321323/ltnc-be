package com.ltnc.be.rest.request;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpsertLeaveAppRequest extends BaseRequest {
  private String reason;
  private Date timeOff;
  private Long employeeId;
}
