package com.ltnc.be.port.facade;

import com.ltnc.be.rest.request.UpsertMedicalRecordRequest;
import com.ltnc.be.rest.response.RecordResponse;

import java.util.List;

public interface MedicalRecordFacade {
    void saveMedicalRecord(UpsertMedicalRecordRequest request);

    void updateRecord(Long recordId, UpsertMedicalRecordRequest request);

    void deleteRecord(Long recordId);

    List<RecordResponse> getAllRecordByPatientId(Long patientId);
}
