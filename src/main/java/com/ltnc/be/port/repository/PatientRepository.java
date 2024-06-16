package com.ltnc.be.port.repository;

import com.ltnc.be.domain.patient.Patient;
import com.ltnc.be.domain.patient.PatientType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
    Optional<Patient> findPatientById(Long patientId);


    @Query("SELECT p FROM Patient p WHERE (:name IS NULL OR p.name LIKE %:name%) AND (:patientType IS NULL OR p.patientType = :patientType)")
    Page<Patient> findPatientBySearchAndCriteria(String name, PatientType patientType, Pageable pageable);

    void save(Patient patient);
    void deleteById(Long patientId);
}
