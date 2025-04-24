package com.pvelilla.backend.hairapp.HairApp.service;

import java.util.List;
import java.util.Optional;

import com.pvelilla.backend.hairapp.HairApp.domain.AddressDTO;

public interface AddressService {

	List<AddressDTO> findAll(Optional<Long> userParam);
	
	AddressDTO findById(Long addressId);
	
	Long save(AddressDTO addressDTO);

	AddressDTO update(Long addressId, AddressDTO addressDTO);

	AddressDTO deleteById(Long addressId);
	
}
