package com.ltnc.be.domain.prescriptionMedicine;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.medicine.Medicine;
import com.ltnc.be.domain.patient.Patient;
import com.ltnc.be.domain.prescription.Prescription;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "prescription_medicine")
public class PrescriptionMedicine extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;


    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "dosage")
    private String dosage;
}
