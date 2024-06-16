package com.ltnc.be.rest.controller;

import com.ltnc.be.domain.patient.Patient;
import com.ltnc.be.domain.room.Room;
import com.ltnc.be.domain.roomEmployee.RoomEmployee;
import com.ltnc.be.port.facade.RoomFacade;
import com.ltnc.be.rest.request.UpsertRoomRequest;
import com.ltnc.be.rest.response.BaseResponse;
import com.ltnc.be.rest.response.RoomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@AllArgsConstructor
public class RoomController {

    private final RoomFacade roomFacade;

    // Create Room
    @PostMapping
    @Operation(tags = "Room APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Void> createRoom(@RequestBody UpsertRoomRequest room) {
        roomFacade.createRoom(room);
        return BaseResponse.empty();
    }

    // Delete Room
    @DeleteMapping("/{roomId}")
    @Operation(tags = "Room APIs")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        roomFacade.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    // Assign Patient to Room
    @PostMapping("/{roomId}/patients/{patientId}")
    @Operation(tags = "Room APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Void> assignPatientToRoom(@PathVariable Long roomId,
                                                  @PathVariable Long patientId,
                                                  @RequestParam int bedNumber) {
        roomFacade.assignPatientToRoom(roomId, patientId, bedNumber);
        return BaseResponse.empty();
    }

    // Assign Employee to Room
    @PostMapping("/{roomId}/employees/{employeeId}")
    @Operation(tags = "Room APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Void> assignEmployeeToRoom(@PathVariable Long roomId, @PathVariable Long employeeId) {
        roomFacade.assignEmployeeToRoom(roomId, employeeId);
        return BaseResponse.empty();
    }
    @GetMapping("/")
    @Operation(tags = "Room APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<RoomResponse>> getAllRooms() {
        return BaseResponse.of(roomFacade.getAllRooms());
    }

    // Delete Room Patient
    @DeleteMapping("/{roomId}/patients/{patientId}")
    @Operation(tags = "Room APIs")
    public BaseResponse<Void> deleteRoomPatient(@PathVariable Long roomId, @PathVariable Long patientId) {
        roomFacade.deleteRoomPatient(roomId, patientId);
        return BaseResponse.empty();
    }

    // Delete Room Employee
    @DeleteMapping("/{roomId}/employees/{employeeId}")
    @Operation(tags = "Room APIs")
    public ResponseEntity<Void> deleteRoomEmployee(@PathVariable Long roomId, @PathVariable Long employeeId) {
        roomFacade.deleteRoomEmployee(roomId, employeeId);
        return ResponseEntity.noContent().build();
    }
}
