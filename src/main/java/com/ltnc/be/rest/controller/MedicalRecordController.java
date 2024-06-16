package com.ltnc.be.rest.controller;

import com.ltnc.be.port.facade.MedicalRecordFacade;
import com.ltnc.be.port.facade.PatientFacade;
import com.ltnc.be.rest.request.UpsertMedicalRecordRequest;
import com.ltnc.be.rest.response.BaseResponse;
import com.ltnc.be.rest.response.RecordResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicalRecords")
@RequiredArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordFacade medicalRecordFacade;

    @GetMapping("/")
    @Operation(tags = "Patient APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<RecordResponse>> getAllRecordByPatientId(@RequestParam Long patientId){
        return BaseResponse.of(medicalRecordFacade.getAllRecordByPatientId(patientId));
    }


    @PostMapping("/create-record")
    @Operation(tags = "Patient APIs")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<Void> createRecord(@RequestBody UpsertMedicalRecordRequest request){
        medicalRecordFacade.saveMedicalRecord(request);
        return BaseResponse.empty();
    }

    @PutMapping("/{recordId}")
    @Operation(tags = "Patient APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Void> updateRecord(@PathVariable Long recordId, @RequestBody UpsertMedicalRecordRequest request){
        medicalRecordFacade.updateRecord(recordId,request);
        return BaseResponse.empty();
    }

    @DeleteMapping("/{recordId}")
    @Operation(tags = "Patient APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Void> deleteRecord(@PathVariable Long recordId){
        medicalRecordFacade.deleteRecord(recordId);
        return  BaseResponse.empty();
    }
}
