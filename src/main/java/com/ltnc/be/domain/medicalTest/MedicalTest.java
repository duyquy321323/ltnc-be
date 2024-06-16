package com.ltnc.be.domain.medicalTest;


import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import com.ltnc.be.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medical_test")
public class MedicalTest extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private TestType testType;

    private String result;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "record_id")
    private MedicalRecord medicalRecord;
}
