package com.ltnc.be.rest.request;

import com.ltnc.be.domain.equipment.Status;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertEquipmentRequest extends BaseRequest {
  private Date inputDate;
  private String supplier;
  private String name;
  private Status status;
  private int quantities;
}
