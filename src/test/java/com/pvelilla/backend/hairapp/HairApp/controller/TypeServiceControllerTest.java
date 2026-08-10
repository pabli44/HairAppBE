package com.pvelilla.backend.hairapp.HairApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvelilla.backend.hairapp.HairApp.domain.TypeServiceDTO;
import com.pvelilla.backend.hairapp.HairApp.service.TypeServiceService;
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

@WebMvcTest(TypeServiceController.class)
class TypeServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeServiceService typeServiceService;

    @Autowired
    private ObjectMapper objectMapper;

    private TypeServiceDTO typeServiceDTO;

    @BeforeEach
    void setUp() {
        typeServiceDTO = new TypeServiceDTO();
        typeServiceDTO.setTypeServiceId(1L);
        typeServiceDTO.setServiceName("Haircut");
        typeServiceDTO.setPrice(100L);
    }

    @Test
    void findALlRecords_ShouldReturnList() throws Exception {
        when(typeServiceService.findAll(any())).thenReturn(Arrays.asList(typeServiceDTO));

        mockMvc.perform(get("/apiv1/typeservices")
                        .param("priceParam", "100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].typeServiceId").value(1L));
    }

    @Test
    void getTypeServiceById_ShouldReturnDetails() throws Exception {
        when(typeServiceService.findById(1L)).thenReturn(typeServiceDTO);

        mockMvc.perform(get("/apiv1/typeservices/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeServiceId").value(1L));
    }

    @Test
    void save_ShouldReturnRecordId() throws Exception {
        when(typeServiceService.save(any(TypeServiceDTO.class))).thenReturn(1L);

        mockMvc.perform(post("/apiv1/typeservices")
                        .content(objectMapper.writeValueAsString(typeServiceDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value(1L));
    }

    @Test
    void updateById_ShouldReturnUpdatedDetails() throws Exception {
        when(typeServiceService.update(eq(1L), any(TypeServiceDTO.class))).thenReturn(typeServiceDTO);

        mockMvc.perform(put("/apiv1/typeservices/1")
                        .content(objectMapper.writeValueAsString(typeServiceDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeServiceId").value(1L));
    }

    @Test
    void deleteById_ShouldReturnDeletedDetails() throws Exception {
        when(typeServiceService.deleteById(1L)).thenReturn(typeServiceDTO);

        mockMvc.perform(delete("/apiv1/typeservices/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeServiceId").value(1L));
    }
}
