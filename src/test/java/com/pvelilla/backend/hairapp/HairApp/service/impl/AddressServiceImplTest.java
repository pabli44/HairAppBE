package com.pvelilla.backend.hairapp.HairApp.service.impl;

import com.pvelilla.backend.hairapp.HairApp.domain.AddressDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.Address;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.AddressRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AddressServiceImpl addressService;

    private Address address;
    private AddressDTO addressDTO;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setAddressId(1L);
        address.setDescription("Main St");
        address.setCity("Anytown");

        addressDTO = new AddressDTO();
        addressDTO.setAddressId(1L);
        addressDTO.setDescription("Main St");
        addressDTO.setCity("Anytown");
    }

    @Test
    void findAll_ShouldReturnListOfAddressDTO() {
        when(addressRepository.findAll()).thenReturn(Arrays.asList(address));
        when(modelMapper.map(any(Address.class), eq(AddressDTO.class))).thenReturn(addressDTO);

        List<AddressDTO> result = addressService.findAll(Optional.empty());

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getDescription()).isEqualTo(addressDTO.getDescription());
        verify(addressRepository).findAll();
    }

    @Test
    void findById_WhenAddressExists_ShouldReturnAddressDTO() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(modelMapper.map(address, AddressDTO.class)).thenReturn(addressDTO);

        AddressDTO result = addressService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getAddressId()).isEqualTo(1L);
        verify(addressRepository).findById(1L);
    }

    @Test
    void findById_WhenAddressDoesNotExist_ShouldThrowRecordNotFoundException() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> addressService.findById(1L))
                .isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void save_ShouldReturnAddressId() {
        when(modelMapper.map(addressDTO, Address.class)).thenReturn(address);
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Long result = addressService.save(addressDTO);

        assertThat(result).isEqualTo(1L);
        verify(addressRepository).save(any(Address.class));
    }

    @Test
    void update_WhenAddressExists_ShouldReturnUpdatedAddressDTO() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(modelMapper.map(addressDTO, Address.class)).thenReturn(address);
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        when(modelMapper.map(any(Address.class), eq(AddressDTO.class))).thenReturn(addressDTO);

        AddressDTO result = addressService.update(1L, addressDTO);

        assertThat(result).isNotNull();
        assertThat(result.getAddressId()).isEqualTo(1L);
        verify(addressRepository).findById(1L);
        verify(addressRepository).save(any(Address.class));
    }

    @Test
    void deleteById_WhenAddressExists_ShouldReturnDeletedAddressDTO() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(modelMapper.map(address, AddressDTO.class)).thenReturn(addressDTO);

        AddressDTO result = addressService.deleteById(1L);

        assertThat(result).isNotNull();
        verify(addressRepository).findById(1L);
        verify(addressRepository).delete(address);
    }
}
