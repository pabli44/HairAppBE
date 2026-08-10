package com.pvelilla.backend.hairapp.HairApp.service.impl;

import com.pvelilla.backend.hairapp.HairApp.domain.TypeServiceDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.TypeService;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.TypeServiceRepository;
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
class TypeServiceServiceImplTest {

    @Mock
    private TypeServiceRepository typeServiceRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TypeServiceServiceImpl typeServiceService;

    private TypeService typeService;
    private TypeServiceDTO typeServiceDTO;

    @BeforeEach
    void setUp() {
        typeService = new TypeService();
        typeService.setTypeServiceId(1L);

        typeServiceDTO = new TypeServiceDTO();
        typeServiceDTO.setTypeServiceId(1L);
    }

    @Test
    void findAll_ShouldReturnListOfTypeServiceDTO() {
        when(typeServiceRepository.findAll()).thenReturn(Arrays.asList(typeService));
        when(modelMapper.map(any(TypeService.class), eq(TypeServiceDTO.class))).thenReturn(typeServiceDTO);

        List<TypeServiceDTO> result = typeServiceService.findAll(Optional.empty());

        assertThat(result).hasSize(1);
        verify(typeServiceRepository).findAll();
    }

    @Test
    void findById_WhenExists_ShouldReturnDTO() {
        when(typeServiceRepository.findById(1L)).thenReturn(Optional.of(typeService));
        when(modelMapper.map(typeService, TypeServiceDTO.class)).thenReturn(typeServiceDTO);

        TypeServiceDTO result = typeServiceService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getTypeServiceId()).isEqualTo(1L);
    }

    @Test
    void findById_WhenDoesNotExist_ShouldThrowException() {
        when(typeServiceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> typeServiceService.findById(1L))
                .isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void save_ShouldReturnId() {
        when(modelMapper.map(typeServiceDTO, TypeService.class)).thenReturn(typeService);
        when(typeServiceRepository.save(any(TypeService.class))).thenReturn(typeService);

        Long result = typeServiceService.save(typeServiceDTO);

        assertThat(result).isEqualTo(1L);
    }

    @Test
    void update_WhenExists_ShouldReturnUpdatedDTO() {
        when(typeServiceRepository.findById(1L)).thenReturn(Optional.of(typeService));
        when(modelMapper.map(typeServiceDTO, TypeService.class)).thenReturn(typeService);
        when(typeServiceRepository.save(any(TypeService.class))).thenReturn(typeService);
        when(modelMapper.map(any(TypeService.class), eq(TypeServiceDTO.class))).thenReturn(typeServiceDTO);

        TypeServiceDTO result = typeServiceService.update(1L, typeServiceDTO);

        assertThat(result).isNotNull();
        verify(typeServiceRepository).save(any(TypeService.class));
    }

    @Test
    void deleteById_WhenExists_ShouldReturnDeletedDTO() {
        when(typeServiceRepository.findById(1L)).thenReturn(Optional.of(typeService));
        when(modelMapper.map(typeService, TypeServiceDTO.class)).thenReturn(typeServiceDTO);

        TypeServiceDTO result = typeServiceService.deleteById(1L);

        assertThat(result).isNotNull();
        verify(typeServiceRepository).delete(typeService);
    }
}
