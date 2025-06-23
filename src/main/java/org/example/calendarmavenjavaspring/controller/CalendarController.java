package org.example.calendarmavenjavaspring.controller;

import jakarta.validation.Valid;
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
    public void addUser(@RequestBody @Valid UserDTO user) {
        calendarService.addUser(user);
    }

    @GetMapping("/api/users/{id}/events")
    public List<EventDTO> getEventsOfUser(@PathVariable Long id) {
        return calendarService.getEventsOfUser(id);
    }

    @GetMapping("/api/users")
    public List<UserDTO> getUsers() {
        return calendarService.getUsers();
    }

    @GetMapping("/api/events/{eventId}")
    public EventDTO getEvent(@PathVariable Long eventId) {
        return calendarService.getEvent(eventId);
    }

    @DeleteMapping("/api/events/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        calendarService.deleteEvent(eventId);
    }

    @PostMapping("/api/addEvent")
    public void addEvent(@RequestBody @Valid EventDTO event) {
        calendarService.addEvent(event);
    }

    @PutMapping("/api/events/{eventId}")
    public void updateEvent(@PathVariable Long eventId, @RequestBody @Valid EventDTO event) {
        calendarService.updateEvent(eventId, event);
    }

}
