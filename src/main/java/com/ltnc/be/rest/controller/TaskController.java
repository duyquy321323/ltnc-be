package com.ltnc.be.rest.controller;


import com.ltnc.be.annotation.IsAuthenticated;
import com.ltnc.be.domain.task.Task;
import com.ltnc.be.port.facade.TaskFacade;
import com.ltnc.be.rest.request.UpsertTaskRequest;
import com.ltnc.be.rest.response.BaseResponse;
import com.ltnc.be.rest.response.TaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    TaskFacade taskFacade;

    @GetMapping("/")
    @Operation(tags = "Task APIs")
    @ResponseStatus(HttpStatus.OK)
    //@IsAuthenticated
    public BaseResponse<List<TaskResponse>> getAllTaskByEmployeeIdAndMonth(@RequestParam Long employeeId, @RequestParam int month){
        return BaseResponse.of(taskFacade.getAllTaskByEmployeeIdAndMonth(employeeId, month));
    }

    @PostMapping("/create-task")
    @Operation(tags = "Task APIs")
    @ResponseStatus(HttpStatus.OK)
    //@IsAuthenticated
    public BaseResponse<Void> createTask(@RequestBody UpsertTaskRequest request){
        taskFacade.createTask(request);
        return BaseResponse.empty();
    }

    @PutMapping("/{taskId}")
    @Operation(tags = "Task APIs")
    @ResponseStatus(HttpStatus.OK)
    //@IsAuthenticated
    public BaseResponse<Void> updateTask(@PathVariable Long taskId, @RequestBody UpsertTaskRequest request){
        taskFacade.updateTask(taskId, request);
        return BaseResponse.empty();
    }

    @DeleteMapping("/{taskId}")
    @Operation(tags = "Task APIs")
    @ResponseStatus(HttpStatus.OK)
    //@IsAuthenticated
    public BaseResponse<Void> deleteTask(@PathVariable Long taskId){
        taskFacade.deleteTask(taskId);
        return BaseResponse.empty();
    }

}
