package com.ltnc.be.port.repository;

import com.ltnc.be.domain.leaveApplication.LeaveApplication;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
  Optional<LeaveApplication> findLeaveApplicationById(Long id);
}
