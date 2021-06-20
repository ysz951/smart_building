package com.smart.building.smart_building_room_meeting.payload;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class CreateDepartment {
    @NotNull
    private String name;

    private Set<Integer> rooms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Integer> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Integer> rooms) {
        this.rooms = rooms;
    }
}
