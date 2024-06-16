package com.ltnc.be.domain.task;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.employee.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
    
@Entity
@Table(name = "task")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity {
    @Column(name = "start_time")
    @Temporal(TemporalType.DATE)
    private Date startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.DATE)
    private Date endTime;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}