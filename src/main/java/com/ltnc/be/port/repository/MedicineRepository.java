package com.ltnc.be.port.repository;

import com.ltnc.be.domain.medicine.Medicine;
import com.ltnc.be.domain.medicineManagement.MedicineManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findByNameContainingIgnoreCase(String name);
}