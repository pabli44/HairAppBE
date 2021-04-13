package com.pvelilla.backend.hairapp.HairApp.service;

import java.util.List;
import java.util.Optional;

import com.pvelilla.backend.hairapp.HairApp.domain.AdressDTO;

public interface AdressService {

	List<AdressDTO> findAll(Optional<Long> userParam);
	
	AdressDTO findById(Long adressId);
	
	Long save(AdressDTO adressDTO);

	AdressDTO update(Long adressId, AdressDTO adressDTO);

	AdressDTO deleteById(Long adressId);
	
}
