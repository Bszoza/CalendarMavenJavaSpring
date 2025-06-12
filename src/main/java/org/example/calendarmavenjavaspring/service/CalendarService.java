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

    public void addEvent(EventDTO event) {
        if (userRepository.existsByNickname(event.getUser().getNickname())) {
            eventRepository.save(dtoMapper.DTOToEvent(event));
        } else throw new IllegalArgumentException("User with nickname not found");
    }

    public List<EventDTO> getEventsOfUser(Long id) {
        return dtoMapper.eventsToDTOs(eventRepository.findByUserId(id));
    }

    public EventDTO getEvent(Long id) {
        if (eventRepository.findById(id).isPresent()) {
            return dtoMapper.eventToDTO(eventRepository.findById(id).get());
        } else throw new EntityNotFoundException();
    }


}
