package org.example.calendarmavenjavaspring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.calendarmavenjavaspring.dto.EventDTO;
import org.example.calendarmavenjavaspring.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalendarControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddUser_Success() throws Exception {
        UserDTO user = new UserDTO("janek", "janek@example.com");

        mockMvc.perform(post("/api/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddUser_ValidationFail() throws Exception {
        UserDTO user = new UserDTO("", "zlyemail");

        mockMvc.perform(post("/api/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Nickname is required")));
    }

    @Test
    public void testAddEvent_Success() throws Exception {
        // najpierw dodaj usera
        UserDTO user = new UserDTO("anna", "anna@example.com");
        mockMvc.perform(post("/api/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        // potem dodaj event
        EventDTO event = new EventDTO(
                "Spotkanie", "Opis", "Biuro",
                Date.valueOf(LocalDate.now()),
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                "anna"
        );

        mockMvc.perform(post("/api/addEvent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddEvent_UserNotFound() throws Exception {
        EventDTO event = new EventDTO(
                "Spotkanie", "Opis", "Biuro",
                Date.valueOf(LocalDate.now()),
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                "nieistnieje"
        );

        mockMvc.perform(post("/api/addEvent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("User with nickname not found")));
    }
}
