package org.example.calendarmavenjavaspring;

import org.example.calendarmavenjavaspring.dto.UserDTO;
import org.example.calendarmavenjavaspring.service.CalendarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalendarServiceTest {

    @Autowired
    CalendarService calendarService;

    @Test
    void shouldAddAndGetUser() {
        UserDTO user = new UserDTO("john", "john@example.com");
        calendarService.addUser(user);
        assertTrue(calendarService.getUsers()
                .stream()
                .anyMatch(u -> u.getNickname().equals("john")));
    }
}
