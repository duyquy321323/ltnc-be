package com.ltnc.be.port.facade;

import com.ltnc.be.domain.task.Task;
import com.ltnc.be.rest.request.UpsertTaskRequest;
import com.ltnc.be.rest.response.TaskResponse;

import java.util.List;

public interface TaskFacade {
    List<TaskResponse> getAllTaskByEmployeeIdAndMonth(Long employeeId, int month);

    void createTask(UpsertTaskRequest request);

    void updateTask(Long taskId, UpsertTaskRequest request);

    void deleteTask(Long taskId);
}
