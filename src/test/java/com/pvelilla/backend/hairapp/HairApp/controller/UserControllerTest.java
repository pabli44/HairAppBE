package com.pvelilla.backend.hairapp.HairApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvelilla.backend.hairapp.HairApp.domain.ProfileDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.UserDTO;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setProfileId(1L);
        profileDTO.setProfileName("ADMIN");

        userDTO = new UserDTO();
        userDTO.setUserId(1L);
        userDTO.setName("John");
        userDTO.setLastName("Doe");
        userDTO.setUserName("johndoe");
        userDTO.setPassword("password");
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPhone("123456789");
        userDTO.setProfile(profileDTO);
    }

    @Test
    void findAllRecords_ShouldReturnList() throws Exception {
        when(userService.findAll(any())).thenReturn(Arrays.asList(userDTO));

        mockMvc.perform(get("/apiv1/users")
                        .param("emailParam", "john.doe@example.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value(userDTO.getEmail()));
    }

    @Test
    void getUserById_ShouldReturnUser() throws Exception {
        when(userService.findById(1L)).thenReturn(userDTO);

        mockMvc.perform(get("/apiv1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()));
    }

    @Test
    void save_ShouldReturnRecordId() throws Exception {
        when(userService.save(any(UserDTO.class))).thenReturn(1L);

        mockMvc.perform(post("/apiv1/users")
                        .content(objectMapper.writeValueAsString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value(1L));
    }

    @Test
    void updateById_ShouldReturnUpdatedUser() throws Exception {
        when(userService.update(eq(1L), any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(put("/apiv1/users/1")
                        .content(objectMapper.writeValueAsString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L));
    }

    @Test
    void deleteById_ShouldReturnDeletedUser() throws Exception {
        when(userService.deleteById(1L)).thenReturn(userDTO);

        mockMvc.perform(delete("/apiv1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L));
    }

    @Test
    void getUserById_WhenNotFound_ShouldReturn404() throws Exception {
        when(userService.findById(99L)).thenThrow(new RecordNotFoundException("User", 99L));

        mockMvc.perform(get("/apiv1/users/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void save_WhenInvalidData_ShouldReturn400() throws Exception {
        userDTO.setName(""); // Invalid because of @NotBlank

        mockMvc.perform(post("/apiv1/users")
                        .content(objectMapper.writeValueAsString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }
}
