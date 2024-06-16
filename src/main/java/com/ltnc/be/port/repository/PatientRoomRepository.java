package com.ltnc.be.port.repository;

import com.ltnc.be.domain.patientRoom.PatientRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRoomRepository extends JpaRepository<PatientRoom, Long> {
    Optional<PatientRoom> findPatientRoomByPatientIdAndRoomId(Long patientId, Long roomId);
}
