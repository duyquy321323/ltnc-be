package com.ltnc.be.port.repository;

import com.ltnc.be.domain.equipment.Equipment;
import com.ltnc.be.domain.equipment.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EquipmentRepository extends PagingAndSortingRepository<Equipment, Long> {
    Optional<Equipment> findEquipmentById(Long id);

    @Query("SELECT e FROM Equipment e WHERE (:name IS NULL OR e.name LIKE %:name%) AND (:status IS NULL OR e.status = :status)")
    Page<Equipment> findEquipmentsBySearchCriteria(String name, Status status, Pageable pageable);

    void save(Equipment equipment);
    void deleteById(Long equipmentId);
}
