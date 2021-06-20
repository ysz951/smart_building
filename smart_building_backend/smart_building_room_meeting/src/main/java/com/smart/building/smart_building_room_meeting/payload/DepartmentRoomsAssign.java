package com.smart.building.smart_building_room_meeting.payload;

import java.util.List;

public class DepartmentRoomsAssign {
    private List<Integer> roomList;

    public List<Integer> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Integer> roomList) {
        this.roomList = roomList;
    }
}
