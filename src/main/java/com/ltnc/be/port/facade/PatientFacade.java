package com.ltnc.be.port.facade;

import com.ltnc.be.domain.patient.PatientType;
import com.ltnc.be.dto.MedicalRecordDTO;
import com.ltnc.be.rest.request.UpsertMedicalRecordRequest;
import com.ltnc.be.rest.request.UpsertPatientRequest;
import com.ltnc.be.rest.response.PatientResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientFacade {
    List<PatientResponse> getAllPatients(Integer pageNo, Integer pageSize, String sortBy);
    List<PatientResponse> searchPatients(String name, PatientType patientType, Integer pageNo, Integer pageSize, String sortBy);

    void savePatient(UpsertPatientRequest request);

    void deletePatientById(Long patientId);

    void updatePatient(Long patientId, UpsertPatientRequest request);
    PatientResponse getPatient(Long id);

}
