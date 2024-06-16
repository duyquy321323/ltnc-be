package com.ltnc.be.rest.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.ltnc.be.domain.patient.PatientType;
import com.ltnc.be.port.facade.PatientFacade;
import com.ltnc.be.rest.request.UpsertMedicalRecordRequest;
import com.ltnc.be.rest.request.UpsertPatientRequest;
import com.ltnc.be.rest.response.BaseResponse;
import com.ltnc.be.rest.response.PatientResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientFacade patientFacade;

    @GetMapping("/")
    @Operation(tags = "Patient APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<PatientResponse>> getAllPatients(@RequestParam(defaultValue = "0") Integer pageNo,
                                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                                              @RequestParam(defaultValue = "id") String sortBy,
                                                              @RequestParam(defaultValue = "0") Boolean searchFlag,
                                                              @RequestParam(required = false) String fullName,
                                                              @RequestParam(required = false)PatientType type){
        if(searchFlag==Boolean.TRUE){
            return BaseResponse.of(patientFacade.searchPatients(fullName,type,pageNo,pageSize,sortBy));
        }
        return BaseResponse.of(patientFacade.getAllPatients(pageNo,pageSize,sortBy));
    }

    @PostMapping("/create-patient")
    @Operation(tags = "Patient APIs")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<Void> createNewPatient(@RequestBody UpsertPatientRequest request){
        patientFacade.savePatient(request);
        return BaseResponse.empty();
    }

    @DeleteMapping("/{patientId}")
    @Operation(tags = "Patient APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Void> deletePatient(@PathVariable Long patientId){
        patientFacade.deletePatientById(patientId);
        return BaseResponse.empty();
    }

    @PatchMapping("/{patientId}")
    @Operation(tags = "Patient APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Void> updatePatient(@PathVariable Long patientId, @RequestBody UpsertPatientRequest request){
        patientFacade.updatePatient(patientId,request);
        return BaseResponse.empty();
    }

    @GetMapping("/{patientId}")
    @Operation(tags = "Patient APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PatientResponse> getPatient(@PathVariable Long patientId){
        return BaseResponse.of(patientFacade.getPatient(patientId));
    }

}
