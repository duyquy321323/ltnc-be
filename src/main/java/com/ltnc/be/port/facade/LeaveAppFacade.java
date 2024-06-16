package com.ltnc.be.port.facade;

import com.ltnc.be.domain.leaveApplication.LeaveApplication;
import com.ltnc.be.rest.request.ResponseLeaveAppRequest;
import com.ltnc.be.rest.request.UpsertLeaveAppRequest;
import com.ltnc.be.rest.response.LeaveAppResponse;

import java.util.List;

public interface LeaveAppFacade {
    List<LeaveAppResponse> getAllLeaveApp();

    void responseLeaveApp(Long leaveAppId, ResponseLeaveAppRequest request);

    void deleteLeaveApp(Long leaveAppId);

    void sendLeaveApp(UpsertLeaveAppRequest request);
}
