package org.example.calendarmavenjavaspring.dto;

import org.example.calendarmavenjavaspring.model.Event;
import org.example.calendarmavenjavaspring.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOMapper {

    public UserDTO userToDTO(User user) {
        return new UserDTO(user.getNickname(), user.getEmail());
    }

    public List<UserDTO> usersToDTOs(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(userToDTO(user));
        }
        return userDTOs;
    }

    public User DTOToUser(UserDTO userDTO) {
        return new User(userDTO.getNickname(), userDTO.getEmail());
    }

    public List<User> DTOsToUsers(List<UserDTO> userDTOs) {
        List<User> users = new ArrayList<>();
        for (UserDTO userDTO : userDTOs) {
            users.add(DTOToUser(userDTO));
        }
        return users;
    }

    public EventDTO eventToDTO(Event event) {
        return new EventDTO(event.getTitle(), event.getDescription(), event.getLocation(), event.getDate(), event.getStartTime(),
                event.getEndTime(), event.getUser());
    }

    public List<EventDTO> eventsToDTOs(List<Event> events) {
        List<EventDTO> dtos = new ArrayList<EventDTO>();
        for (Event event : events) {
            dtos.add(eventToDTO(event));
        }
        return dtos;
    }

    public Event DTOToEvent(EventDTO eventDTO) {
        return new Event(eventDTO.getTitle(), eventDTO.getDescription(), eventDTO.getLocation(), eventDTO.getDate(),
                eventDTO.getStartTime(), eventDTO.getEndTime(), eventDTO.getUser());
    }

    public List<Event> DTOsToEvents(List<EventDTO> eventDTOs) {
        List<Event> events = new ArrayList<>();
        for (EventDTO eventDTO : eventDTOs) {
            events.add(DTOToEvent(eventDTO));
        }
        return events;
    }

}
