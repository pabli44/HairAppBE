package com.pvelilla.backend.hairapp.HairApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvelilla.backend.hairapp.HairApp.domain.*;
import com.pvelilla.backend.hairapp.HairApp.service.ServiceDetailsService;
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

@WebMvcTest(ServiceDetailsController.class)
class ServiceDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceDetailsService serviceDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    private ServiceDetailsDTO serviceDetailsDTO;

    @BeforeEach
    void setUp() {
        serviceDetailsDTO = new ServiceDetailsDTO();
        serviceDetailsDTO.setServiceDetailsId(1L);
        serviceDetailsDTO.setService(new ServiceEDTO());
        serviceDetailsDTO.setClient(new UserDTO());
        serviceDetailsDTO.setValue(100.0);
        serviceDetailsDTO.setDate(new java.util.Date());
        serviceDetailsDTO.setHour("10:00");
        serviceDetailsDTO.setQuantity(1L);
        serviceDetailsDTO.setProfessional(new UserDTO());
        serviceDetailsDTO.setAddress(new AddressDTO());
    }

    @Test
    void findALlRecords_ShouldReturnList() throws Exception {
        when(serviceDetailsService.findAll(any())).thenReturn(Arrays.asList(serviceDetailsDTO));

        mockMvc.perform(get("/apiv1/servicedetails")
                        .param("serviceParam", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].serviceDetailsId").value(1L));
    }

    @Test
    void getServiceDetailsById_ShouldReturnDetails() throws Exception {
        when(serviceDetailsService.findById(1L)).thenReturn(serviceDetailsDTO);

        mockMvc.perform(get("/apiv1/servicedetails/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serviceDetailsId").value(1L));
    }

    @Test
    void save_ShouldReturnRecordId() throws Exception {
        when(serviceDetailsService.save(any(ServiceDetailsDTO.class))).thenReturn(1L);

        mockMvc.perform(post("/apiv1/servicedetails")
                        .content(objectMapper.writeValueAsString(serviceDetailsDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value(1L));
    }

    @Test
    void updateById_ShouldReturnUpdatedDetails() throws Exception {
        when(serviceDetailsService.update(eq(1L), any(ServiceDetailsDTO.class))).thenReturn(serviceDetailsDTO);

        mockMvc.perform(put("/apiv1/servicedetails/1")
                        .content(objectMapper.writeValueAsString(serviceDetailsDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serviceDetailsId").value(1L));
    }

    @Test
    void deleteById_ShouldReturnDeletedDetails() throws Exception {
        when(serviceDetailsService.deleteById(1L)).thenReturn(serviceDetailsDTO);

        mockMvc.perform(delete("/apiv1/servicedetails/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serviceDetailsId").value(1L));
    }

    @Test
    void findALlRecordsByClient_ShouldReturnList() throws Exception {
        when(serviceDetailsService.findAllByClient(any())).thenReturn(Arrays.asList(serviceDetailsDTO));

        mockMvc.perform(get("/apiv1/servicedetails/client")
                        .param("clientParam", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].serviceDetailsId").value(1L));
    }

    @Test
    void findALlRecordsByProfessional_ShouldReturnList() throws Exception {
        when(serviceDetailsService.findAllByProfessional(any())).thenReturn(Arrays.asList(serviceDetailsDTO));

        mockMvc.perform(get("/apiv1/servicedetails/professional")
                        .param("professionalParam", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].serviceDetailsId").value(1L));
    }
}
