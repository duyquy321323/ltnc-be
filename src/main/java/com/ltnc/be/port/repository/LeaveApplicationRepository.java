package com.ltnc.be.port.repository;

import com.ltnc.be.domain.leaveApplication.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
    Optional<LeaveApplication> findLeaveApplicationById(Long id);
}
