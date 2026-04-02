package com.pvelilla.backend.hairapp.HairApp.service.impl;

import com.pvelilla.backend.hairapp.HairApp.domain.TransactionEDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.TransactionE;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private TransactionE transaction;
    private TransactionEDTO transactionDTO;

    @BeforeEach
    void setUp() {
        transaction = new TransactionE();
        transaction.setTransactionId(1L);

        transactionDTO = new TransactionEDTO();
        transactionDTO.setTransactionId(1L);
    }

    @Test
    void findAll_ShouldReturnListOfTransactionDTO() {
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction));
        when(modelMapper.map(any(TransactionE.class), eq(TransactionEDTO.class))).thenReturn(transactionDTO);

        List<TransactionEDTO> result = transactionService.findAll(Optional.empty());

        assertThat(result).hasSize(1);
        verify(transactionRepository).findAll();
    }

    @Test
    void findById_WhenExists_ShouldReturnDTO() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        when(modelMapper.map(transaction, TransactionEDTO.class)).thenReturn(transactionDTO);

        TransactionEDTO result = transactionService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getTransactionId()).isEqualTo(1L);
    }

    @Test
    void findById_WhenDoesNotExist_ShouldThrowException() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> transactionService.findById(1L))
                .isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void save_ShouldReturnId() {
        when(modelMapper.map(transactionDTO, TransactionE.class)).thenReturn(transaction);
        when(transactionRepository.save(any(TransactionE.class))).thenReturn(transaction);

        Long result = transactionService.save(transactionDTO);

        assertThat(result).isEqualTo(1L);
    }

    @Test
    void update_WhenExists_ShouldReturnUpdatedDTO() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        when(modelMapper.map(transactionDTO, TransactionE.class)).thenReturn(transaction);
        when(transactionRepository.save(any(TransactionE.class))).thenReturn(transaction);
        when(modelMapper.map(any(TransactionE.class), eq(TransactionEDTO.class))).thenReturn(transactionDTO);

        TransactionEDTO result = transactionService.update(1L, transactionDTO);

        assertThat(result).isNotNull();
        verify(transactionRepository).save(any(TransactionE.class));
    }

    @Test
    void deleteById_WhenExists_ShouldReturnDeletedDTO() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        when(modelMapper.map(transaction, TransactionEDTO.class)).thenReturn(transactionDTO);

        TransactionEDTO result = transactionService.deleteById(1L);

        assertThat(result).isNotNull();
        verify(transactionRepository).delete(transaction);
    }
}
