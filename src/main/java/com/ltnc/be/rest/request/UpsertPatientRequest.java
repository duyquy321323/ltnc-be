package com.ltnc.be.rest.request;

import com.ltnc.be.domain.patient.PatientType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertPatientRequest extends BaseRequest {
  private String name;
  private String BHYT;
  private String phone;
  private String gender;
  private String address;
  private Date dob;
  private PatientType type;
}
