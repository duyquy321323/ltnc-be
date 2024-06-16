package com.ltnc.be.dto;

import com.ltnc.be.domain.equipment.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDTO {
    private Long id;
    private Date inputDate;
    private String supplier;
    private String name;
    private Status status;
    private int quantities;
    private List<Long> employeeIds;  // Include only IDs for simplicity and performance

    // Static method to transform from domain model to DTO
    public static EquipmentDTO fromDomain(com.ltnc.be.domain.equipment.Equipment equipment) {
        return EquipmentDTO.builder()
                .id(equipment.getId())
                .inputDate(equipment.getInputDate())
                .supplier(equipment.getSupplier())
                .name(equipment.getName())
                .status(equipment.getStatus())
                .quantities(equipment.getQuantities())
                .employeeIds(equipment.getEmployees().stream()
                        .map(employee -> employee.getId())
                        .collect(Collectors.toList()))
                .build();
    }
}
