package com.ltnc.be.port.repository;

import com.ltnc.be.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT t FROM Task t " +
            "WHERE t.employee.id = :employeeId " +
            "AND (:month IS NULL OR MONTH(t.startTime) = :month)")
    Optional<List<Task>> getAllTasksByEmployeeIdAndCriteria(@Param("employeeId") Long employeeId, @Param("month") int month);

    Optional<Task> findTaskById(Long taskId);
}
