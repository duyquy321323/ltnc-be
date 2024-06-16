package com.ltnc.be.adapter.facade;

import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.exception.EntityNotFoundException;
import com.ltnc.be.domain.patient.Patient;
import com.ltnc.be.domain.patientRoom.PatientRoom;
import com.ltnc.be.domain.room.Room;
import com.ltnc.be.domain.roomEmployee.RoomEmployee;
import com.ltnc.be.dto.RoomDTO;
import com.ltnc.be.port.facade.RoomFacade;
import com.ltnc.be.port.repository.EmployeeRepository;
import com.ltnc.be.port.repository.PatientRepository;
import com.ltnc.be.port.repository.PatientRoomRepository;
import com.ltnc.be.port.repository.RoomRepository;
import com.ltnc.be.rest.exception.CapacityExceededException;
import com.ltnc.be.rest.exception.EmployeeLimitExceededException;
import com.ltnc.be.rest.request.UpsertRoomRequest;
import com.ltnc.be.rest.response.RoomResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomFacadeImpl implements RoomFacade {

    private final RoomRepository roomRepository;
    private final PatientRepository patientRepository;
    private final EmployeeRepository employeeRepository;
    private final PatientRoomRepository patientRoomRepository;

    @Override
    @Transactional
    public void createRoom(UpsertRoomRequest room) {
        var roomEntity = Room.builder()
            .roomCapacity(room.getRoomCapacity())
            .roomNumber(room.getRoomNumber())
            .build();
        roomRepository.save(roomEntity);
    }

    @Override
    @Transactional
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    @Override
    @Transactional
    @SneakyThrows
    public void assignPatientToRoom(Long roomId, Long patientId, int bedNumber){
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        Patient patient = patientRepository.findPatientById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));

        if (room.getPatientRooms().size() >= room.getRoomCapacity()) {
            throw new CapacityExceededException("Capacity exceeded for the room.");
        }

        PatientRoom patientRoom = new PatientRoom(patient, room, bedNumber, System.currentTimeMillis(), null);
        room.getPatientRooms().add(patientRoom);
        roomRepository.save(room);
    }

    @Override
    @Transactional
    @SneakyThrows
    public void assignEmployeeToRoom(Long roomId, Long employeeId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        Employee employee = employeeRepository.findEmployeeById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));

        if (room.getRoomEmployees().size() >= 3) {
            throw new EmployeeLimitExceededException("Employee limit exceeded for the room.");
        }

        RoomEmployee roomEmployee = new RoomEmployee(room, employee, System.currentTimeMillis(), null);
        room.getRoomEmployees().add(roomEmployee);
        roomRepository.save(room);
    }
    @Override
    public List<RoomResponse> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomResponse> roomResponses = new ArrayList<>();
        for(Room room: rooms){
            roomResponses.add(RoomResponse.toRoomResponse(RoomDTO.fromDomain(room)));
        }
        return roomResponses;
    }

    @Override
    @Transactional
    @SneakyThrows
    public void deleteRoomPatient(Long roomId, Long patientId) {
        // Find the room by roomId
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // Remove the PatientRoom associated with the given patientId from the room's patientRooms
        room.getPatientRooms().removeIf(patientRoom -> patientRoom.getPatient().getId().equals(patientId));

        // Save the room to trigger the cascade deletion if configured properly
        roomRepository.save(room);
    }

    @Override
    @Transactional
    public void deleteRoomEmployee(Long roomId, Long employeeId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        room.getRoomEmployees().removeIf(roomEmployee -> roomEmployee.getEmployee().getId().equals(employeeId));
        roomRepository.save(room);
    }
}
