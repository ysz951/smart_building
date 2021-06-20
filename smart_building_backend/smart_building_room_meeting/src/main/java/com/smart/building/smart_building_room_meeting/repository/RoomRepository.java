package com.smart.building.smart_building_room_meeting.repository;

import com.smart.building.smart_building_room_meeting.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT r from Room r where r.depart is NULL")
    List<Room> findAllIdleRoom();
}
