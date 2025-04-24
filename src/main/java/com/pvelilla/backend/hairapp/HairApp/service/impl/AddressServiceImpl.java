package com.pvelilla.backend.hairapp.HairApp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pvelilla.backend.hairapp.HairApp.domain.AddressDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.Address;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.AddressRepository;
import com.pvelilla.backend.hairapp.HairApp.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	private AddressRepository addressRepository;
	private static final String NAME_DOMAIN = "Address";
	
	
	public AddressServiceImpl(final AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	
	@Override
	public List<AddressDTO> findAll(Optional<Long> userParam) {
		Map<String, Object> paramSpec = new HashMap<>();
		userParam.ifPresent(mapper -> paramSpec.put("userParam", userParam.get()));

		return addressRepository.findAll().stream().map(mapper -> new ModelMapper().map(mapper, AddressDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public AddressDTO findById(Long addressId) {
		return addressRepository.findById(addressId)
				.map(mapper -> new ModelMapper().map(mapper, AddressDTO.class))
				.orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, addressId));
	}

	@Override
	public Long save(AddressDTO addressDTO) {
		Address address = new ModelMapper().map(addressDTO, Address.class);
		addressRepository.save(address);
		return address.getAddressId();
	}

	@Override
	public AddressDTO update(Long addressId, AddressDTO addressDTO) {
		return addressRepository.findById(addressId).map(mapper -> {
			Address address = new ModelMapper().map(addressDTO, Address.class);
			address.setAddressId(addressId);
			addressRepository.save(address);
			return new ModelMapper().map(address, AddressDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, addressId));
	}

	@Override
	public AddressDTO deleteById(Long addressId) {
		return addressRepository.findById(addressId).map(mapper -> {
			addressRepository.delete(mapper);
			return new ModelMapper().map(mapper, AddressDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, addressId));
	}

}
