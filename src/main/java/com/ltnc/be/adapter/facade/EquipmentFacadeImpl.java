package com.ltnc.be.adapter.facade;

import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.equipment.Equipment;
import com.ltnc.be.domain.equipment.Status;
import com.ltnc.be.domain.exception.EntityNotFoundException;
import com.ltnc.be.dto.EquipmentDTO;
import com.ltnc.be.port.facade.EquipmentFacade;
import com.ltnc.be.port.repository.EmployeeRepository;
import com.ltnc.be.port.repository.EquipmentRepository;
import com.ltnc.be.rest.request.UpsertEquipmentRequest;
import com.ltnc.be.rest.response.EquipmentResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EquipmentFacadeImpl implements EquipmentFacade {
    private final EquipmentRepository equipmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EquipmentResponse> getAllEquipments(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        var equipments = equipmentRepository.findAll(paging);
        if(equipments.hasContent()){
            return equipments.getContent().stream()
                    .map(EquipmentDTO::fromDomain)
                    .map(EquipmentResponse::toEquipmentResponse)
                    .collect(Collectors.toList());
        }else{
            return  new ArrayList<>();
        }
    }

    @Override
    public List<EquipmentResponse> searchEquipments(String name, Status status, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        var equipments = equipmentRepository.findEquipmentsBySearchCriteria(name,status,paging);
        if(equipments.hasContent()){
            return equipments.getContent().stream()
                    .map(EquipmentDTO::fromDomain)
                    .map(EquipmentResponse::toEquipmentResponse)
                    .collect(Collectors.toList());
        }else{
            return  new ArrayList<>();
        }
    }

    @Override
    @SneakyThrows
    public void saveEquipment(UpsertEquipmentRequest request) {
        // find all equipment manager
        Optional<List<Employee>> optionalEmployees = employeeRepository.findAllByDutyType(DutyType.EQUIPMENT_MANAGER);
        List<Employee> employees = new ArrayList<>();
        if (optionalEmployees.isPresent()){
            employees = optionalEmployees.get();
        }else throw new EntityNotFoundException();

        Equipment equipment = Equipment.builder()
                .name(request.getName())
                .quantities(request.getQuantities())
                .inputDate(request.getInputDate())
                .status(request.getStatus())
                .supplier(request.getSupplier())
                .build();

        equipment.setEmployees(employees);
        equipmentRepository.save(equipment);
    }

    @Override
    @SneakyThrows
    public void deleteEquipment(Long equipmentId) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findEquipmentById(equipmentId);
        if(optionalEquipment.isPresent()){
            equipmentRepository.deleteById(equipmentId);
        }else throw new EntityNotFoundException();
    }
}
