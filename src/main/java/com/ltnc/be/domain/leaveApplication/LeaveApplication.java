package com.ltnc.be.domain.leaveApplication;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.employee.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "leave_application")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApplication extends BaseEntity {
    @Column(name = "reason")
    private String reason;

    @Column(name = "time_off")
    @Temporal(TemporalType.DATE)
    private Date timeOff;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
