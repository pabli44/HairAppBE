package com.pvelilla.backend.hairapp.HairApp.service;

import java.util.List;
import java.util.Optional;

import com.pvelilla.backend.hairapp.HairApp.domain.ServiceDetailsDTO;

public interface ServiceDetailsService {

	List<ServiceDetailsDTO> findAll(Optional<Long> serviceParam);
	
	ServiceDetailsDTO findById(Long serviceDetailsId);
	
	Long save(ServiceDetailsDTO serviceDetailsDTO);

	ServiceDetailsDTO update(Long serviceDetailsId, ServiceDetailsDTO serviceDetailsDTO);

	ServiceDetailsDTO deleteById(Long serviceDetailsId);
	
}
