package com.ltnc.be.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UpsertMedicalRecordRequest extends BaseRequest{
    private String diagnostic;
    private Date hospitalizedTime;
    private Date leaveTime;
    private Long doctorId;
    private Long patientId;
}
