package org.example.calendarmavenjavaspring.controller;

import org.example.calendarmavenjavaspring.dto.EventDTO;
import org.example.calendarmavenjavaspring.dto.UserDTO;
import org.example.calendarmavenjavaspring.service.CalendarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CalendarController {
    CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/api/addUser")
    public void addUser(@RequestBody UserDTO user) {
        calendarService.addUser(user);
    }

    @PostMapping("/api/addEvent")
    public void addEvent(@RequestBody EventDTO event) {
        calendarService.addEvent(event);
    }

    @GetMapping("/api/users/{id}/events")
    public List<EventDTO> getEventsOfUser(@PathVariable Long id) {
        return calendarService.getEventsOfUser(id);
    }

    @GetMapping("/api/events/{eventId}")
    public EventDTO getEvent(@PathVariable Long eventId) {
        return calendarService.getEvent(eventId);
    }

}
