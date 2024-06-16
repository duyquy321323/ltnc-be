package com.ltnc.be.port.repository;

import com.ltnc.be.domain.patientRoom.PatientRoom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRoomRepository extends JpaRepository<PatientRoom, Long> {
  Optional<PatientRoom> findPatientRoomByPatientIdAndRoomId(Long patientId, Long roomId);
}
