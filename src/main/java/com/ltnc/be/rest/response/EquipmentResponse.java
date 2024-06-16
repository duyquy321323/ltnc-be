package com.ltnc.be.rest.response;

import com.ltnc.be.domain.equipment.Status;
import com.ltnc.be.dto.EquipmentDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentResponse {
    private Long equipmentId;
    private Date inputDate;
    private String supplier;
    private String name;
    private Status status;
    private int quantities;
    private List<Long> employeeIds;

    public static EquipmentResponse toEquipmentResponse(EquipmentDTO equipmentDTO){
        return EquipmentResponse.builder()
                .equipmentId(equipmentDTO.getId())
                .inputDate(equipmentDTO.getInputDate())
                .supplier(equipmentDTO.getSupplier())
                .name(equipmentDTO.getName())
                .quantities(equipmentDTO.getQuantities())
                .status(equipmentDTO.getStatus())
                .employeeIds(equipmentDTO.getEmployeeIds())
                .build();
    }
}
