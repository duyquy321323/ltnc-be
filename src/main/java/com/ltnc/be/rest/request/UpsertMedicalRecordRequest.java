package com.ltnc.be.rest.request;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UpsertMedicalRecordRequest extends BaseRequest {
  private String diagnostic;
  private Date hospitalizedTime;
  private Date leaveTime;
  private Long doctorId;
  private Long patientId;
}
