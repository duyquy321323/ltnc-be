package com.ltnc.be.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UpsertMedicineManagementRequest extends BaseRequest{
    private Long medicineId;
    private Long inputQuantity;
    private String supplier;
    private Long employeeId;
    private Long expiredDate;
    private String code;
}
