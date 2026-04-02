package com.pvelilla.backend.hairapp.HairApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvelilla.backend.hairapp.HairApp.domain.ServiceEDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.TypeServiceDTO;
import com.pvelilla.backend.hairapp.HairApp.service.ServiceService;
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

@WebMvcTest(ServiceController.class)
class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceService serviceService;

    @Autowired
    private ObjectMapper objectMapper;

    private ServiceEDTO serviceDTO;

    @BeforeEach
    void setUp() {
        TypeServiceDTO typeServiceDTO = new TypeServiceDTO();
        typeServiceDTO.setTypeServiceId(1L);

        serviceDTO = new ServiceEDTO();
        serviceDTO.setServiceId(1L);
        serviceDTO.setTypeService(typeServiceDTO);
        serviceDTO.setState("ACTIVE");
    }

    @Test
    void findALlRecords_ShouldReturnList() throws Exception {
        when(serviceService.findAll(any())).thenReturn(Arrays.asList(serviceDTO));

        mockMvc.perform(get("/apiv1/services")
                        .param("typeServiceParam", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].serviceId").value(1L));
    }

    @Test
    void getServiceById_ShouldReturnService() throws Exception {
        when(serviceService.findById(1L)).thenReturn(serviceDTO);

        mockMvc.perform(get("/apiv1/services/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serviceId").value(1L));
    }

    @Test
    void save_ShouldReturnRecordId() throws Exception {
        when(serviceService.save(any(ServiceEDTO.class))).thenReturn(1L);

        mockMvc.perform(post("/apiv1/services")
                        .content(objectMapper.writeValueAsString(serviceDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value(1L));
    }

    @Test
    void updateById_ShouldReturnUpdatedService() throws Exception {
        when(serviceService.update(eq(1L), any(ServiceEDTO.class))).thenReturn(serviceDTO);

        mockMvc.perform(put("/apiv1/services/1")
                        .content(objectMapper.writeValueAsString(serviceDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serviceId").value(1L));
    }

    @Test
    void deleteById_ShouldReturnDeletedService() throws Exception {
        when(serviceService.deleteById(1L)).thenReturn(serviceDTO);

        mockMvc.perform(delete("/apiv1/services/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serviceId").value(1L));
    }
}
