package com.pvelilla.backend.hairapp.HairApp.service.impl;

import com.pvelilla.backend.hairapp.HairApp.domain.ServiceEDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.ServiceE;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.ServiceRepository;
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
class ServiceServiceImplTest {

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    private ServiceE service;
    private ServiceEDTO serviceDTO;

    @BeforeEach
    void setUp() {
        service = new ServiceE();
        service.setServiceId(1L);

        serviceDTO = new ServiceEDTO();
        serviceDTO.setServiceId(1L);
    }

    @Test
    void findAll_ShouldReturnListOfServiceDTO() {
        when(serviceRepository.findAll()).thenReturn(Arrays.asList(service));
        when(modelMapper.map(any(ServiceE.class), eq(ServiceEDTO.class))).thenReturn(serviceDTO);

        List<ServiceEDTO> result = serviceService.findAll(Optional.empty());

        assertThat(result).hasSize(1);
        verify(serviceRepository).findAll();
    }

    @Test
    void findById_WhenExists_ShouldReturnDTO() {
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(service));
        when(modelMapper.map(service, ServiceEDTO.class)).thenReturn(serviceDTO);

        ServiceEDTO result = serviceService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getServiceId()).isEqualTo(1L);
    }

    @Test
    void findById_WhenDoesNotExist_ShouldThrowException() {
        when(serviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> serviceService.findById(1L))
                .isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void save_ShouldReturnId() {
        when(modelMapper.map(serviceDTO, ServiceE.class)).thenReturn(service);
        when(serviceRepository.save(any(ServiceE.class))).thenReturn(service);

        Long result = serviceService.save(serviceDTO);

        assertThat(result).isEqualTo(1L);
    }

    @Test
    void update_WhenExists_ShouldReturnUpdatedDTO() {
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(service));
        when(modelMapper.map(serviceDTO, ServiceE.class)).thenReturn(service);
        when(serviceRepository.save(any(ServiceE.class))).thenReturn(service);
        when(modelMapper.map(any(ServiceE.class), eq(ServiceEDTO.class))).thenReturn(serviceDTO);

        ServiceEDTO result = serviceService.update(1L, serviceDTO);

        assertThat(result).isNotNull();
        verify(serviceRepository).save(any(ServiceE.class));
    }

    @Test
    void deleteById_WhenExists_ShouldReturnDeletedDTO() {
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(service));
        when(modelMapper.map(service, ServiceEDTO.class)).thenReturn(serviceDTO);

        ServiceEDTO result = serviceService.deleteById(1L);

        assertThat(result).isNotNull();
        verify(serviceRepository).delete(service);
    }
}
