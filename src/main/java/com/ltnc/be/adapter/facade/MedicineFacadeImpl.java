package com.ltnc.be.adapter.facade;

import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.exception.EntityNotFoundException;
import com.ltnc.be.domain.medicine.Medicine;
import com.ltnc.be.domain.medicineManagement.MedicineManagement;
import com.ltnc.be.dto.MedicineManagementDTO;
import com.ltnc.be.port.facade.MedicineFacade;
import com.ltnc.be.port.repository.EmployeeRepository;
import com.ltnc.be.port.repository.MedicineManagementRepository;
import com.ltnc.be.port.repository.MedicineRepository;
import com.ltnc.be.rest.request.UpsertMedicineManagementRequest;
import com.ltnc.be.rest.request.UpsertMedicineRequest;
import com.ltnc.be.rest.response.MedicineManagementResponse;
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
public class MedicineFacadeImpl implements MedicineFacade {
    private final MedicineRepository medicineRepository;
    private final MedicineManagementRepository medicineManagementRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<MedicineManagementResponse> getAllMedicineManagements(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        var medicines = medicineManagementRepository.findAll(paging);
        if (medicines.hasContent()) {
            return medicines.getContent().stream()
                    .map(MedicineManagementDTO::fromDomain)
                    .map(MedicineManagementResponse::toMedicineManagementResponse)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<MedicineManagementResponse> getMedicinesManagementsByName(String name, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        var medicines = medicineManagementRepository.findByName(name, paging);
        if (medicines.hasContent()) {
            return medicines.getContent().stream()
                    .map(MedicineManagementDTO::fromDomain)
                    .map(MedicineManagementResponse::toMedicineManagementResponse)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public List<Medicine> getMedicinesByName(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @SneakyThrows
    public void createMedicine(UpsertMedicineRequest medicineRequest) {
        Optional<List<Employee>> optionalEmployees = employeeRepository.findAllByDutyType(DutyType.MEDICINE_MANAGER);
        List<Employee> employees = new ArrayList<>();
        if (optionalEmployees.isPresent()){
            employees = optionalEmployees.get();
        }else throw new EntityNotFoundException();
        Medicine medicine = Medicine.builder()
                        .name(medicineRequest.getName())
                                .medicineType(medicineRequest.getMedicineType())
                                        .medicalUseType(medicineRequest.getMedicalUseType())
                                                .ingredient(medicineRequest.getIngredient())
                                                        .price(medicineRequest.getPrice())
                                                                .build();
        medicineRepository.save(medicine);
    }

    @Override
    @SneakyThrows
    public void updateMedicine(Long medicineId, UpsertMedicineRequest medicineRequest) {
        Medicine existingMedicine = medicineRepository.findById(medicineId).orElse(null);
        if (existingMedicine == null) {
            throw new EntityNotFoundException();
        }

        if(medicineRequest.getName()!=null) existingMedicine.setName(medicineRequest.getName());
        if(medicineRequest.getMedicineType()!=null) existingMedicine.setMedicineType(medicineRequest.getMedicineType());
        if(medicineRequest.getMedicalUseType()!=null) existingMedicine.setMedicalUseType(medicineRequest.getMedicalUseType());
        if(medicineRequest.getIngredient()!=null) existingMedicine.setIngredient(medicineRequest.getIngredient());
        if(medicineRequest.getPrice()!=null) existingMedicine.setPrice(medicineRequest.getPrice());
        medicineRepository.save(existingMedicine);
    }

    @Override
    public void deleteMedicine(Long medicineId) {
        medicineRepository.deleteById(medicineId);
    }

    @Override
    public void createMedicineManagement(UpsertMedicineManagementRequest medicine) {
        MedicineManagement medicineManagement = new MedicineManagement();
        var medicineEntity = medicineRepository.findById(medicine.getMedicineId()).orElse(null);
        if (medicineEntity != null) {
            medicineManagement.setMedicine(medicineEntity);
        }
        medicineManagementRepository.save(medicineManagement);
    }

    @Override
    public void updateMedicineManagement(Long medicineId, UpsertMedicineManagementRequest medicine) {
        MedicineManagement existingMedicineManagement = medicineManagementRepository.findById(medicineId).orElse(null);
        if (existingMedicineManagement != null) {
            medicineManagementRepository.save(existingMedicineManagement);
        }
    }

    @Override
    public void deleteMedicineManagement(Long medicineId) {
        medicineManagementRepository.deleteById(medicineId);
    }
}
