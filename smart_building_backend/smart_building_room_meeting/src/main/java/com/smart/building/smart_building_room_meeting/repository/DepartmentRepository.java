package com.smart.building.smart_building_room_meeting.repository;

import com.smart.building.smart_building_room_meeting.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
