package com.ltnc.be.adapter.facade;

import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.exception.EntityNotFoundException;
import com.ltnc.be.domain.leaveApplication.LeaveApplication;
import com.ltnc.be.domain.leaveApplication.Status;
import com.ltnc.be.dto.LeaveApplicationDTO;
import com.ltnc.be.port.facade.LeaveAppFacade;
import com.ltnc.be.port.repository.EmployeeRepository;
import com.ltnc.be.port.repository.LeaveApplicationRepository;
import com.ltnc.be.rest.request.ResponseLeaveAppRequest;
import com.ltnc.be.rest.request.UpsertLeaveAppRequest;
import com.ltnc.be.rest.response.LeaveAppResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LeaveAppFacadeImpl implements LeaveAppFacade {
    private final LeaveApplicationRepository leaveApplicationRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    public List<LeaveAppResponse> getAllLeaveApp() {
        var leaveApps = leaveApplicationRepository.findAll();
        List<LeaveAppResponse> leaveAppResponses = new ArrayList<>();
        for (LeaveApplication leaveApplication:leaveApps){
            leaveAppResponses.add(LeaveAppResponse.toLeaveAppResponse(LeaveApplicationDTO.fromDomain(leaveApplication)));
        }
        return leaveAppResponses;
    }

    @Override
    @SneakyThrows
    public void responseLeaveApp(Long leaveAppId, ResponseLeaveAppRequest request) {
        Optional<LeaveApplication> optionalLeaveApplication=leaveApplicationRepository.findLeaveApplicationById(leaveAppId);
        if(optionalLeaveApplication.isPresent()){
            LeaveApplication leaveApplication = optionalLeaveApplication.get();
            leaveApplication.setStatus(request.getStatus());
            leaveApplicationRepository.save(leaveApplication);
        }else  throw new EntityNotFoundException();
    }

    @Override
    @SneakyThrows
    public void deleteLeaveApp(Long leaveAppId) {
        Optional<LeaveApplication> optionalLeaveApplication=leaveApplicationRepository.findLeaveApplicationById(leaveAppId);
        if(optionalLeaveApplication.isPresent()){
            leaveApplicationRepository.deleteById(leaveAppId);
        }else  throw new EntityNotFoundException();
    }

    @Override
    @SneakyThrows
    public void sendLeaveApp(UpsertLeaveAppRequest request) {
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeById(request.getEmployeeId());
        if (optionalEmployee.isPresent()){
            LeaveApplication leaveApplication = LeaveApplication.builder()
                    .reason(request.getReason())
                    .timeOff(request.getTimeOff())
                    .employee(optionalEmployee.get())
                    .status(Status.PENDING)
                    .build();
            leaveApplicationRepository.save(leaveApplication);
        }else throw new EntityNotFoundException();
    }
}
