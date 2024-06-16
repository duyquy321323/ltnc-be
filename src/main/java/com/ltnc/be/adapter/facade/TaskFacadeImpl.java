package com.ltnc.be.adapter.facade;

import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.exception.EntityNotFoundException;
import com.ltnc.be.domain.task.Task;
import com.ltnc.be.dto.TaskDTO;
import com.ltnc.be.port.facade.TaskFacade;
import com.ltnc.be.port.repository.EmployeeRepository;
import com.ltnc.be.port.repository.TaskRepository;
import com.ltnc.be.rest.request.UpsertTaskRequest;
import com.ltnc.be.rest.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskFacadeImpl implements TaskFacade {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;


    @Override
    public List<TaskResponse> getAllTaskByEmployeeIdAndMonth(Long employeeId, int month) {
        Optional<List<Task>> optionalTasks = taskRepository.getAllTasksByEmployeeIdAndCriteria(employeeId,month);
        List<TaskResponse> taskResponses = new ArrayList<>();
        if(optionalTasks.isPresent()){
            for(Task task: optionalTasks.get()){
                TaskDTO taskDTO = TaskDTO.fromDomain(task);
                TaskResponse taskResponse = TaskResponse.toTaskResponse(taskDTO);
                taskResponses.add(taskResponse);
            }
        }
        return taskResponses;
    }

    @Override
    @SneakyThrows
    public void createTask(UpsertTaskRequest request) {
        // find employee implement task
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeById(request.getEmployeeId());
        if(optionalEmployee.isPresent()){
            Task task = Task.builder()
                    .startTime(request.getStartTime())
                    .endTime(request.getEndTime())
                    .description(request.getDescription())
                    .status(request.getStatus())
                    .employee(optionalEmployee.get())
                    .build();
            taskRepository.save(task);
        }else throw new EntityNotFoundException();
    }

    @Override
    @SneakyThrows
    public void updateTask(Long taskId, UpsertTaskRequest request) {
        Optional<Task> optionalTask = taskRepository.findTaskById(taskId);
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeById(request.getEmployeeId());
        if(optionalTask.isPresent() && optionalEmployee.isPresent()){
            Task task = optionalTask.get();
            if (request.getStartTime() != null) {
                task.setStartTime(request.getStartTime());
            }
            if (request.getEndTime() != null) {
                task.setEndTime(request.getEndTime());
            }
            if (request.getDescription() != null) {
                task.setDescription(request.getDescription());
            }
            if (request.getStatus() != null) {
                task.setStatus(request.getStatus());
            }
            task.setEmployee(optionalEmployee.get());
            taskRepository.save(task);
        }else throw new EntityNotFoundException();
    }

    @Override
    @SneakyThrows
    public void deleteTask(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findTaskById(taskId);
        if(optionalTask.isPresent()){
            taskRepository.deleteById(taskId);
        }else throw new EntityNotFoundException();
    }

}
