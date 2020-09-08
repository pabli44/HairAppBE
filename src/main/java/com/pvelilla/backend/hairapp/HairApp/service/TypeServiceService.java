package com.pvelilla.backend.hairapp.HairApp.service;

import java.util.List;
import java.util.Optional;

import com.pvelilla.backend.hairapp.HairApp.domain.TypeServiceDTO;

public interface TypeServiceService {

	List<TypeServiceDTO> findAll(Optional<Long> priceParam);
	
	TypeServiceDTO findById(Long typeServiceId);
	
	Long save(TypeServiceDTO typeServiceDTO);

	TypeServiceDTO update(Long typeServiceId, TypeServiceDTO typeServiceDTO);

	TypeServiceDTO deleteById(Long typeServiceId);
	
}
