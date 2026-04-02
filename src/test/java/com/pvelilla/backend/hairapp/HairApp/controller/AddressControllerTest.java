package com.pvelilla.backend.hairapp.HairApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvelilla.backend.hairapp.HairApp.domain.AddressDTO;
import com.pvelilla.backend.hairapp.HairApp.service.AddressService;
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

@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Autowired
    private ObjectMapper objectMapper;

    private AddressDTO addressDTO;

    @BeforeEach
    void setUp() {
        addressDTO = new AddressDTO();
        addressDTO.setAddressId(1L);
        addressDTO.setDescription("Main St");
        addressDTO.setCity("Anytown");
        addressDTO.setPrincipal("Y");
        addressDTO.setUser(1L);
    }

    @Test
    void findALlRecords_ShouldReturnList() throws Exception {
        when(addressService.findAll(any())).thenReturn(Arrays.asList(addressDTO));

        mockMvc.perform(get("/apiv1/addresses")
                        .param("userParam", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value(addressDTO.getDescription()));
    }

    @Test
    void getAddressById_ShouldReturnAddress() throws Exception {
        when(addressService.findById(1L)).thenReturn(addressDTO);

        mockMvc.perform(get("/apiv1/addresses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressId").value(1L));
    }

    @Test
    void save_ShouldReturnRecordId() throws Exception {
        when(addressService.save(any(AddressDTO.class))).thenReturn(1L);

        mockMvc.perform(post("/apiv1/addresses")
                        .content(objectMapper.writeValueAsString(addressDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value(1L));
    }

    @Test
    void updateById_ShouldReturnUpdatedAddress() throws Exception {
        when(addressService.update(eq(1L), any(AddressDTO.class))).thenReturn(addressDTO);

        mockMvc.perform(put("/apiv1/addresses/1")
                        .content(objectMapper.writeValueAsString(addressDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressId").value(1L));
    }

    @Test
    void deleteById_ShouldReturnDeletedAddress() throws Exception {
        when(addressService.deleteById(1L)).thenReturn(addressDTO);

        mockMvc.perform(delete("/apiv1/addresses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressId").value(1L));
    }
}
