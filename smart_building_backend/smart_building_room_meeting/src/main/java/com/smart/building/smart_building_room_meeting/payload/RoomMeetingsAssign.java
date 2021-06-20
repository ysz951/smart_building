package com.smart.building.smart_building_room_meeting.payload;

import java.util.List;

public class RoomMeetingsAssign {
    private List<Integer> meetingList;

    public List<Integer> getMeetingList() {
        return meetingList;
    }

    public void setMeetingList(List<Integer> meetingList) {
        this.meetingList = meetingList;
    }
}
