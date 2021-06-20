package com.smart.building.smart_building_room_meeting.service;

import com.smart.building.smart_building_room_meeting.model.Department;
import com.smart.building.smart_building_room_meeting.model.Room;
import com.smart.building.smart_building_room_meeting.payload.CreateDepartment;
import com.smart.building.smart_building_room_meeting.payload.DepartmentRoomsAssign;
import com.smart.building.smart_building_room_meeting.repository.DepartmentRepository;
import com.smart.building.smart_building_room_meeting.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoomRepository roomRepository;

    public String assignRoom(int id, DepartmentRoomsAssign departmentRooms) {
        Department department = departmentRepository.findById(id).get();
//        for (Room depart_room : department.getRooms()) {
//            depart_room.setDepart(null);
//            roomRepository.save(depart_room);
//        }
        List<Integer> roomList = departmentRooms.getRoomList();
        for (int roomId : roomList) {
            Room room = roomRepository.findById(roomId).get();
            if (room.getDepart() == null) {
                room.setDepart(department);
                roomRepository.save(room);
            }
        }
        return "Change Department rooms";
    }

    public String deleteDepartment(int id) {
        Department department = departmentRepository.findById(id).get();
        for (Room depart_room : department.getRooms()) {
            depart_room.setDepart(null);
            roomRepository.save(depart_room);
        }
        departmentRepository.delete(department);
        return "delete department";
    }

    public void createDepartment(CreateDepartment createDepartment) {
        Department department = new Department();
        department.setName(createDepartment.getName());
        Department depart = departmentRepository.save(department);
        if (createDepartment.getRooms() != null) {
            for (int roomId : createDepartment.getRooms()) {
                Room room = roomRepository.findById(roomId).get();
                if (room.getDepart() == null) {
                    room.setDepart(depart);
                    roomRepository.save(room);
                }
            }
        }
    }
}
