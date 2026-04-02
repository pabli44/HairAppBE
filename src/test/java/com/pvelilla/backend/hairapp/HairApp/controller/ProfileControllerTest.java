package com.pvelilla.backend.hairapp.HairApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvelilla.backend.hairapp.HairApp.domain.ProfileDTO;
import com.pvelilla.backend.hairapp.HairApp.service.ProfileService;
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

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProfileDTO profileDTO;

    @BeforeEach
    void setUp() {
        profileDTO = new ProfileDTO();
        profileDTO.setProfileId(1L);
        profileDTO.setProfileName("ADMIN");
    }

    @Test
    void findALlRecords_ShouldReturnList() throws Exception {
        when(profileService.findAll(any())).thenReturn(Arrays.asList(profileDTO));

        mockMvc.perform(get("/apiv1/profiles")
                        .param("profileNameParam", "ADMIN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].profileName").value(profileDTO.getProfileName()));
    }

    @Test
    void getProfileById_ShouldReturnProfile() throws Exception {
        when(profileService.findById(1L)).thenReturn(profileDTO);

        mockMvc.perform(get("/apiv1/profiles/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profileId").value(1L));
    }

    @Test
    void save_ShouldReturnRecordId() throws Exception {
        when(profileService.save(any(ProfileDTO.class))).thenReturn(1L);

        mockMvc.perform(post("/apiv1/profiles")
                        .content(objectMapper.writeValueAsString(profileDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value(1L));
    }

    @Test
    void updateById_ShouldReturnUpdatedProfile() throws Exception {
        when(profileService.update(eq(1L), any(ProfileDTO.class))).thenReturn(profileDTO);

        mockMvc.perform(put("/apiv1/profiles/1")
                        .content(objectMapper.writeValueAsString(profileDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profileId").value(1L));
    }

    @Test
    void deleteById_ShouldReturnDeletedProfile() throws Exception {
        when(profileService.deleteById(1L)).thenReturn(profileDTO);

        mockMvc.perform(delete("/apiv1/profiles/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profileId").value(1L));
    }
}
