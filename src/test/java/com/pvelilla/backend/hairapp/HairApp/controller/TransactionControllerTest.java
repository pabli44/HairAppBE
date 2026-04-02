package com.pvelilla.backend.hairapp.HairApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvelilla.backend.hairapp.HairApp.domain.TransactionEDTO;
import com.pvelilla.backend.hairapp.HairApp.service.TransactionService;
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

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    private TransactionEDTO transactionDTO;

    @BeforeEach
    void setUp() {
        transactionDTO = new TransactionEDTO();
        transactionDTO.setTransactionId(1L);
        transactionDTO.setTypeTransaction(1L);
        transactionDTO.setState(1L);
    }

    @Test
    void findALlRecords_ShouldReturnList() throws Exception {
        when(transactionService.findAll(any())).thenReturn(Arrays.asList(transactionDTO));

        mockMvc.perform(get("/apiv1/transactions")
                        .param("typeTransactionParam", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transactionId").value(1L));
    }

    @Test
    void getTransactionById_ShouldReturnTransaction() throws Exception {
        when(transactionService.findById(1L)).thenReturn(transactionDTO);

        mockMvc.perform(get("/apiv1/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(1L));
    }

    @Test
    void save_ShouldReturnRecordId() throws Exception {
        when(transactionService.save(any(TransactionEDTO.class))).thenReturn(1L);

        mockMvc.perform(post("/apiv1/transactions")
                        .content(objectMapper.writeValueAsString(transactionDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value(1L));
    }

    @Test
    void updateById_ShouldReturnUpdatedTransaction() throws Exception {
        when(transactionService.update(eq(1L), any(TransactionEDTO.class))).thenReturn(transactionDTO);

        mockMvc.perform(put("/apiv1/transactions/1")
                        .content(objectMapper.writeValueAsString(transactionDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(1L));
    }

    @Test
    void deleteById_ShouldReturnDeletedTransaction() throws Exception {
        when(transactionService.deleteById(1L)).thenReturn(transactionDTO);

        mockMvc.perform(delete("/apiv1/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(1L));
    }
}
