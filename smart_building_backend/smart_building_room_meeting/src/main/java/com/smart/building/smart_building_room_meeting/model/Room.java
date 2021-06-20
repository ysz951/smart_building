package com.smart.building.smart_building_room_meeting.model;

import com.fasterxml.jackson.annotation.*;
import com.smart.building.smart_building_room_meeting.audit.DateAudit;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Room extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Min(value = 1)
    private int capacity;

    @JsonBackReference(value="department_room")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
//    @ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
    private Department depart;

//    @ManyToMany
//    @JoinTable(name = "room_meeting",
//            joinColumns = @JoinColumn(name = "room_id"),
//            inverseJoinColumns = @JoinColumn(name = "meeting_id"))
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
//    @JsonIdentityReference(alwaysAsId=true)
    @OneToMany(
            mappedBy="room",
            targetEntity=Meeting.class
    )
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<Meeting> meeting = new HashSet<>();

    public Room() {

    }

    public Room(int capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Department getDepart() {
        return depart;
    }

    public void setDepart(Department depart) {
        this.depart = depart;
    }

    public Set<Meeting> getMeeting() {
        return meeting;
    }

    public void setMeeting(Set<Meeting> meeting) {
        this.meeting = meeting;
    }
}
