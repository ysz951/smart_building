package com.smart.building.smart_building_room_meeting.repository;

import com.smart.building.smart_building_room_meeting.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
    @Query("SELECT m from Meeting m where m.room is NULL")
    List<Meeting> findAllIdleMeeting();
}
