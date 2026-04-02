package com.pvelilla.backend.hairapp.HairApp.service.impl;

import com.pvelilla.backend.hairapp.HairApp.domain.ServiceDetailsDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.ServiceDetails;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.ServiceDetailsRepository;
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
class ServiceDetailsServiceImplTest {

    @Mock
    private ServiceDetailsRepository serviceDetailsRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ServiceDetailsServiceImpl serviceDetailsService;

    private ServiceDetails serviceDetails;
    private ServiceDetailsDTO serviceDetailsDTO;

    @BeforeEach
    void setUp() {
        serviceDetails = new ServiceDetails();
        serviceDetails.setServiceDetailsId(1L);

        serviceDetailsDTO = new ServiceDetailsDTO();
        serviceDetailsDTO.setServiceDetailsId(1L);
    }

    @Test
    void findAll_ShouldReturnListOfServiceDetailsDTO() {
        when(serviceDetailsRepository.findAll()).thenReturn(Arrays.asList(serviceDetails));
        when(modelMapper.map(any(ServiceDetails.class), eq(ServiceDetailsDTO.class))).thenReturn(serviceDetailsDTO);

        List<ServiceDetailsDTO> result = serviceDetailsService.findAll(Optional.empty());

        assertThat(result).hasSize(1);
        verify(serviceDetailsRepository).findAll();
    }

    @Test
    void findById_WhenExists_ShouldReturnDTO() {
        when(serviceDetailsRepository.findById(1L)).thenReturn(Optional.of(serviceDetails));
        when(modelMapper.map(serviceDetails, ServiceDetailsDTO.class)).thenReturn(serviceDetailsDTO);

        ServiceDetailsDTO result = serviceDetailsService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getServiceDetailsId()).isEqualTo(1L);
    }

    @Test
    void findById_WhenDoesNotExist_ShouldThrowException() {
        when(serviceDetailsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> serviceDetailsService.findById(1L))
                .isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void save_ShouldReturnId() {
        when(modelMapper.map(serviceDetailsDTO, ServiceDetails.class)).thenReturn(serviceDetails);
        when(serviceDetailsRepository.save(any(ServiceDetails.class))).thenReturn(serviceDetails);

        Long result = serviceDetailsService.save(serviceDetailsDTO);

        assertThat(result).isEqualTo(1L);
    }

    @Test
    void update_WhenExists_ShouldReturnUpdatedDTO() {
        when(serviceDetailsRepository.findById(1L)).thenReturn(Optional.of(serviceDetails));
        when(modelMapper.map(serviceDetailsDTO, ServiceDetails.class)).thenReturn(serviceDetails);
        when(serviceDetailsRepository.save(any(ServiceDetails.class))).thenReturn(serviceDetails);
        when(modelMapper.map(any(ServiceDetails.class), eq(ServiceDetailsDTO.class))).thenReturn(serviceDetailsDTO);

        ServiceDetailsDTO result = serviceDetailsService.update(1L, serviceDetailsDTO);

        assertThat(result).isNotNull();
        verify(serviceDetailsRepository).save(any(ServiceDetails.class));
    }

    @Test
    void deleteById_WhenExists_ShouldReturnDeletedDTO() {
        when(serviceDetailsRepository.findById(1L)).thenReturn(Optional.of(serviceDetails));
        when(modelMapper.map(serviceDetails, ServiceDetailsDTO.class)).thenReturn(serviceDetailsDTO);

        ServiceDetailsDTO result = serviceDetailsService.deleteById(1L);

        assertThat(result).isNotNull();
        verify(serviceDetailsRepository).delete(serviceDetails);
    }
}
