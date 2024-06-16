package com.ltnc.be.port.repository;

import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
  Optional<MedicalRecord> findMedicalRecordById(Long id);

  List<MedicalRecord> findAllByPatientId(Long patientId);
}
