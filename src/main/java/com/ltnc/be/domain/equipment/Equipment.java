package com.ltnc.be.domain.equipment;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.employee.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "equipment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Equipment extends BaseEntity {
    @Column(name = "input_date")
    @Temporal(TemporalType.DATE)
    private Date inputDate;

    @Column(name = "supplier")
    private  String supplier;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;
    private int quantities;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "manage_equipment",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;
}
