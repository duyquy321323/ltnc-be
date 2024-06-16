package com.ltnc.be.port.facade;

import com.ltnc.be.domain.room.Room;
import com.ltnc.be.rest.request.UpsertRoomRequest;
import com.ltnc.be.rest.response.RoomResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomFacade {
    void createRoom(UpsertRoomRequest room);
    void deleteRoom(Long roomId);
    void assignPatientToRoom(Long roomId, Long patientId, int bedNumber);
    void assignEmployeeToRoom(Long roomId, Long employeeId);


    List<RoomResponse> getAllRooms();

    @Transactional
    void deleteRoomPatient(Long roomId, Long patientId);

    @Transactional
    void deleteRoomEmployee(Long roomId, Long employeeId);
}
