package com.ltnc.be.domain.medicalRecord;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.medicalTest.MedicalTest;
import com.ltnc.be.domain.patient.Patient;
import com.ltnc.be.domain.prescription.Prescription;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medical_record")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord extends BaseEntity {
    @Column(name ="diagnostic")
    private String diagnostic;
    @Column(name="hospitalized_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hospitalizedTime;
    @Column(name="leave_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveTime;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "handle_record",
            joinColumns = @JoinColumn(name = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;

    @OneToMany(mappedBy = "medicalRecord")
    private List<MedicalTest> medicalTests;

    @OneToMany(mappedBy = "medicalRecord", fetch = FetchType.EAGER)
    private List<Prescription> prescriptions;
}
