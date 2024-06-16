package com.ltnc.be.adapter.facade;

import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.exception.EntityNotFoundException;
import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import com.ltnc.be.domain.patient.Patient;
import com.ltnc.be.dto.MedicalRecordDTO;
import com.ltnc.be.port.facade.MedicalRecordFacade;
import com.ltnc.be.port.repository.EmployeeRepository;
import com.ltnc.be.port.repository.MedicalRecordRepository;
import com.ltnc.be.port.repository.PatientRepository;
import com.ltnc.be.rest.request.UpsertMedicalRecordRequest;
import com.ltnc.be.rest.response.RecordResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MedicalRecordFacadeImpl implements MedicalRecordFacade {
    private final EmployeeRepository employeeRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    @Override
    @SneakyThrows
    public void saveMedicalRecord(UpsertMedicalRecordRequest request) {
        // find doctor create this record and handle patient
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeById(request.getDoctorId());
        List<Employee> doctors = new ArrayList<>();
        if(optionalEmployee.isPresent()){
            DutyType doctorType = optionalEmployee.get().getDutyType();
            if(doctorType==DutyType.DOCTOR_LEVER_1 || doctorType==DutyType.DOCTOR_LEVER_2 || doctorType==DutyType.NURSER){
                doctors.add(optionalEmployee.get());
            }else throw new EntityNotFoundException();
        }else throw new EntityNotFoundException();

        // find patient is created new record
        Optional<Patient> optionalPatient = patientRepository.findPatientById(request.getPatientId());
        if(optionalPatient.isPresent()){
            MedicalRecord medicalRecord = MedicalRecord.builder()
                    .diagnostic(request.getDiagnostic())
                    .hospitalizedTime((request.getHospitalizedTime()))
                    .leaveTime(request.getLeaveTime())
                    .patient(optionalPatient.get())
                    .employees(doctors)
                    .build();
            medicalRecordRepository.save(medicalRecord);
        }else throw new EntityNotFoundException();
    }

    @Override
    @SneakyThrows
    public void updateRecord(Long recordId, UpsertMedicalRecordRequest request) {
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecordRepository.findMedicalRecordById(recordId);
        if (optionalMedicalRecord.isPresent()){
            MedicalRecord medicalRecord = optionalMedicalRecord.get();
            if(request.getHospitalizedTime()!=null) medicalRecord.setHospitalizedTime(request.getHospitalizedTime());
            if(request.getLeaveTime()!=null) medicalRecord.setLeaveTime(request.getLeaveTime());
            if(request.getDiagnostic()!=null) medicalRecord.setDiagnostic(request.getDiagnostic());

            if(request.getDoctorId()!=null){
                // find doctor create this record and handle patient
                Optional<Employee> optionalEmployee = employeeRepository.findEmployeeById(request.getDoctorId());
                if(optionalEmployee.isPresent()){
                    DutyType doctorType = optionalEmployee.get().getDutyType();
                    if(doctorType==DutyType.DOCTOR_LEVER_1 || doctorType==DutyType.DOCTOR_LEVER_2 || doctorType==DutyType.NURSER){
                        medicalRecord.getEmployees().add(optionalEmployee.get());
                    }else throw new EntityNotFoundException();
                }else throw new EntityNotFoundException();
            }
            medicalRecordRepository.save(medicalRecord);
        }else throw new EntityNotFoundException();

    }

    @Override
    @SneakyThrows
    public void deleteRecord(Long recordId) {
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecordRepository.findMedicalRecordById(recordId);
        if(optionalMedicalRecord.isPresent()){
            medicalRecordRepository.deleteById(recordId);
        }else throw new EntityNotFoundException();
    }

    @Override
    public List<RecordResponse> getAllRecordByPatientId(Long patientId) {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllByPatientId(patientId);
        if (medicalRecords.isEmpty()) return new ArrayList<>();
        return medicalRecords.stream().map(MedicalRecordDTO::fromDomain).map(RecordResponse::toResponse).collect(Collectors.toList());
    }


}
