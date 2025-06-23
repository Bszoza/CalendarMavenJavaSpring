package org.example.calendarmavenjavaspring.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.calendarmavenjavaspring.dto.DTOMapper;
import org.example.calendarmavenjavaspring.dto.EventDTO;
import org.example.calendarmavenjavaspring.dto.UserDTO;
import org.example.calendarmavenjavaspring.model.User;
import org.example.calendarmavenjavaspring.repository.EventRepository;
import org.example.calendarmavenjavaspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final DTOMapper dtoMapper;

    public CalendarService(UserRepository userRepository, EventRepository eventRepository, DTOMapper dtoMapper) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.dtoMapper = dtoMapper;
    }

    public void addUser(UserDTO user) {
        userRepository.save(dtoMapper.DTOToUser(user));
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return dtoMapper.usersToDTOs(users);
    }

    public void addEvent(EventDTO event) {
        if (userRepository.existsByNickname(event.getNickname())) {
            eventRepository.save(dtoMapper.DTOToEvent(event));
        } else throw new IllegalArgumentException("User with nickname not found");
    }

    public List<EventDTO> getEventsOfUser(Long id) {
        if (userRepository.existsById(id)) {
            return dtoMapper.eventsToDTOs(eventRepository.findByUserId(id));
        } else throw new IllegalArgumentException("User with this id not found");
    }

    public EventDTO getEvent(Long id) {
        return eventRepository.findById(id).map(dtoMapper::eventToDTO)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        } else throw new EntityNotFoundException();
    }

    public void updateEvent(Long id, EventDTO event) {
        if (eventRepository.findById(id).isPresent() && userRepository.existsByNickname(event.getNickname())) {
            eventRepository.save(dtoMapper.DTOToEvent(event));
        } else throw new IllegalArgumentException("User with nickname '" + event.getNickname() + "' not found");
    }


}
