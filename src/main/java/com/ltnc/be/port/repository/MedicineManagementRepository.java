package com.ltnc.be.port.repository;

import com.ltnc.be.domain.medicineManagement.MedicineManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicineManagementRepository extends JpaRepository<MedicineManagement, Long> {
    @Query("SELECT mm FROM MedicineManagement mm JOIN mm.medicine m WHERE lower(m.name) LIKE lower(concat('%', :name, '%'))")
    Page<MedicineManagement> findByName(String name, Pageable pageable);
}
