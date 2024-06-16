package com.ltnc.be.rest.request;

import com.ltnc.be.domain.medicine.MedicineType;
import com.ltnc.be.domain.medicine.MedicineUseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertMedicineRequest extends BaseRequest {
    private String name;
    private MedicineType medicineType;
    private MedicineUseType medicalUseType;
    private String price;
    private String ingredient;
}
