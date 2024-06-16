package com.ltnc.be.port.repository;

import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    Optional<MedicalRecord> findMedicalRecordById(Long id);

    List<MedicalRecord> findAllByPatientId(Long patientId);
}
