package com.ltnc.be.port.facade;

import com.ltnc.be.domain.medicine.Medicine;
import com.ltnc.be.domain.medicineManagement.MedicineManagement;
import com.ltnc.be.rest.request.UpsertMedicineManagementRequest;
import com.ltnc.be.rest.request.UpsertMedicineRequest;
import com.ltnc.be.rest.response.MedicineManagementResponse;

import java.util.List;

public interface MedicineFacade {
    List<MedicineManagementResponse> getAllMedicineManagements(Integer pageNo, Integer pageSize, String sortBy);
    List<MedicineManagementResponse> getMedicinesManagementsByName(String name, Integer pageNo, Integer pageSize, String sortBy);

    List<Medicine> getAllMedicines();
    List<Medicine> getMedicinesByName(String name);
    void createMedicine(UpsertMedicineRequest medicineRequest);
    void updateMedicine(Long medicineId, UpsertMedicineRequest medicineRequest);
    void deleteMedicine(Long medicineId);

    void createMedicineManagement(UpsertMedicineManagementRequest medicine);
    void updateMedicineManagement(Long medicineId, UpsertMedicineManagementRequest medicine);
    void deleteMedicineManagement(Long medicineId);
}
