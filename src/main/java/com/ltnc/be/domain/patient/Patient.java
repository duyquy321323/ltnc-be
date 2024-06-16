package com.ltnc.be.domain.patient;


import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import com.ltnc.be.domain.medicalTest.MedicalTest;
import com.ltnc.be.domain.patientRoom.PatientRoom;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Table(name = "patient")
public class Patient extends BaseEntity {
    @Column(name = "BHYT", unique = true, nullable = false)
    private String BHYT;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "gender")
    private String gender;
    @Column(name = "address")
    private String address;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "patient_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PatientType patientType;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MedicalTest> medicalTests;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientRoom> patientRooms;
}
