package com.ltnc.be.port.repository;

import com.ltnc.be.domain.roomEmployee.RoomEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomEmployeeRepository extends JpaRepository<RoomEmployee, Long> {
}
