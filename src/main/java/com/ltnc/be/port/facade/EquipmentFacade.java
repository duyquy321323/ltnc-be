package com.ltnc.be.port.facade;

import com.ltnc.be.domain.equipment.Equipment;
import com.ltnc.be.domain.equipment.Status;
import com.ltnc.be.rest.request.UpsertEquipmentRequest;
import com.ltnc.be.rest.response.EquipmentResponse;

import java.util.List;

public interface EquipmentFacade {
    List<EquipmentResponse> getAllEquipments(Integer pageNo, Integer pageSize, String sortBy);

    List<EquipmentResponse> searchEquipments(String name, Status status, Integer pageNo, Integer pageSize, String sortBy);

    void saveEquipment(UpsertEquipmentRequest request);

    void deleteEquipment(Long equipmentId);

}
