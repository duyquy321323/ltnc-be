package com.ltnc.be.rest.controller;

import com.ltnc.be.annotation.IsAuthenticated;
import com.ltnc.be.domain.equipment.Status;
import com.ltnc.be.port.facade.EquipmentFacade;
import com.ltnc.be.rest.request.UpsertEquipmentRequest;
import com.ltnc.be.rest.response.BaseResponse;
import com.ltnc.be.rest.response.EquipmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/equipments")
public class EquipmentController {
    private final EquipmentFacade equipmentFacade;

    @GetMapping("/")
    @Operation(tags = "Equipment APIs")
    @ResponseStatus(HttpStatus.OK)
    //@IsAuthenticated
    public BaseResponse<List<EquipmentResponse>> getAllEquipments(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                                  @RequestParam(defaultValue = "id") String sortBy,
                                                                  @RequestParam(defaultValue = "0") Boolean searchFlag,
                                                                  @RequestParam(required = false) String name,
                                                                  @RequestParam(required = false) Status status){
        if (searchFlag==Boolean.TRUE){
            return BaseResponse.of(equipmentFacade.searchEquipments(name,status,pageNo,pageSize,sortBy));
        }
        return BaseResponse.of(equipmentFacade.getAllEquipments(pageNo,pageSize,sortBy));
    }

    @PostMapping("/save-equipment")
    @Operation(tags = "Equipment APIs")
    @ResponseStatus(HttpStatus.CREATED)
    //@IsAuthenticated
    public BaseResponse<Void> AddNewEquipment(@RequestBody UpsertEquipmentRequest request){
        equipmentFacade.saveEquipment(request);
        return BaseResponse.empty();
    }

    @DeleteMapping("/{equipmentId}")
    @Operation(tags = "Equipment APIs")
    @ResponseStatus(HttpStatus.OK)
    //@IsAuthenticated
    public BaseResponse<Void> DeleteEquipment(@PathVariable Long equipmentId){
        equipmentFacade.deleteEquipment(equipmentId);
        return BaseResponse.empty();
    }
}
