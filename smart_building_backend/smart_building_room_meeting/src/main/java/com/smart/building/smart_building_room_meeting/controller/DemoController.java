package com.smart.building.smart_building_room_meeting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping
    public String welcome() {
        return "room";
    }

    @GetMapping("/other")
    public String other() {
        return "roomother";
    }

    @PostMapping
    public String post() {
        return "roomPost";
    }
}
