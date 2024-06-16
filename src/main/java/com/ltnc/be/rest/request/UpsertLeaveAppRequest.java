package com.ltnc.be.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpsertLeaveAppRequest extends BaseRequest{
    private String reason;
    private Date timeOff;
    private Long employeeId;
}
