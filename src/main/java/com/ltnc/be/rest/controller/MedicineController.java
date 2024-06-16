package com.ltnc.be.rest.controller;

import com.ltnc.be.domain.medicine.Medicine;
import com.ltnc.be.port.facade.MedicineFacade;
import com.ltnc.be.rest.request.UpsertMedicineManagementRequest;
import com.ltnc.be.rest.request.UpsertMedicineRequest;
import com.ltnc.be.rest.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medicines")
@AllArgsConstructor
public class MedicineController {
  private final MedicineFacade medicineFacade;

  @GetMapping("/")
  @Operation(tags = "Medicine APIs", summary = "Get all medicines or search medicines by name")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<List<Medicine>> getMedicines(
      @RequestParam(required = false) String name,
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "name") String sortBy,
      @RequestParam(defaultValue = "false") Boolean searchFlag) {
    if (searchFlag) {
      List<Medicine> medicines = medicineFacade.getMedicinesByName(name);
      return BaseResponse.of(medicines);
    } else {
      List<Medicine> medicines = medicineFacade.getAllMedicines();
      return BaseResponse.of(medicines);
    }
  }

  @PostMapping("/create")
  @Operation(tags = "Medicine APIs", summary = "Create a new medicine")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseResponse<Void> createMedicine(@RequestBody UpsertMedicineRequest medicineRequest) {
    medicineFacade.createMedicine(medicineRequest);
    return BaseResponse.empty();
  }

  @PutMapping("/update/{medicineId}")
  @Operation(tags = "Medicine APIs", summary = "Update an existing medicine")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> updateMedicine(
      @PathVariable Long medicineId, @RequestBody UpsertMedicineRequest medicineRequest) {
    medicineFacade.updateMedicine(medicineId, medicineRequest);
    return BaseResponse.empty();
  }

  @DeleteMapping("/delete/{medicineId}")
  @Operation(tags = "Medicine APIs", summary = "Delete a medicine")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public BaseResponse<Void> deleteMedicine(@PathVariable Long medicineId) {
    medicineFacade.deleteMedicine(medicineId);
    return BaseResponse.empty();
  }

  @PostMapping("/management/create")
  @Operation(tags = "Medicine Management APIs", summary = "Create a new medicine management")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseResponse<Void> createMedicineManagement(
      @RequestBody UpsertMedicineManagementRequest medicineManagementRequest) {
    medicineFacade.createMedicineManagement(medicineManagementRequest);
    return BaseResponse.empty();
  }

  @PutMapping("/management/update/{medicineManagementId}")
  @Operation(tags = "Medicine Management APIs", summary = "Update an existing medicine management")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> updateMedicineManagement(
      @PathVariable Long medicineManagementId,
      @RequestBody UpsertMedicineManagementRequest medicineManagementRequest) {
    medicineFacade.updateMedicineManagement(medicineManagementId, medicineManagementRequest);
    return BaseResponse.empty();
  }

  @DeleteMapping("/management/delete/{medicineManagementId}")
  @Operation(tags = "Medicine Management APIs", summary = "Delete a medicine management")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public BaseResponse<Void> deleteMedicineManagement(@PathVariable Long medicineManagementId) {
    medicineFacade.deleteMedicineManagement(medicineManagementId);
    return BaseResponse.empty();
  }
}
