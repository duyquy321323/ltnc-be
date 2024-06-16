package com.ltnc.be.rest.request;

import com.ltnc.be.domain.leaveApplication.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseLeaveAppRequest extends BaseRequest {
    private Status status;
}
