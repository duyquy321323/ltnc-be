package com.ltnc.be.domain.prescription;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import com.ltnc.be.domain.patient.Patient;
import com.ltnc.be.domain.prescriptionMedicine.PrescriptionMedicine;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "prescription")
public class Prescription extends BaseEntity {
  @Column(name = "user_manual")
  private String userManual;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "employee_id")
  private Employee prescriber;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "prescription")
  private List<PrescriptionMedicine> prescriptionMedicines;

  @ManyToOne(fetch = FetchType.LAZY)
  private MedicalRecord medicalRecord;
}
