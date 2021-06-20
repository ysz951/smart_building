package com.smart.building.smart_building_room_meeting.controller;

import com.smart.building.smart_building_room_meeting.exception.ResourceNotFoundException;
import com.smart.building.smart_building_room_meeting.model.Department;
import com.smart.building.smart_building_room_meeting.payload.ApiResponse;
import com.smart.building.smart_building_room_meeting.payload.CreateDepartment;
import com.smart.building.smart_building_room_meeting.payload.DepartmentRoomsAssign;
import com.smart.building.smart_building_room_meeting.repository.DepartmentRepository;
import com.smart.building.smart_building_room_meeting.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable("id") int id) {
        return departmentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
    }

    @PostMapping("/save")
    public String createDepartment(@RequestBody @Valid CreateDepartment createDepartment) {
        departmentService.createDepartment(createDepartment);
        return "ok";
    }

    @PutMapping("/{id}/assign")
    public String assignRoom(@PathVariable("id") int id, @RequestBody DepartmentRoomsAssign departmentRooms) {
        return departmentService.assignRoom(id, departmentRooms);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable("id") int id) {
        return departmentService.deleteDepartment(id);
    }
}
