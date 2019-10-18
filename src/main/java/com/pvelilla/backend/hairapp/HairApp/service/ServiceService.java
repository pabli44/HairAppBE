package com.pvelilla.backend.hairapp.HairApp.service;

import java.util.List;
import java.util.Optional;

import com.pvelilla.backend.hairapp.HairApp.domain.ServiceDTO;

public interface ServiceService {
	
	List<ServiceDTO> findAll(Optional<String> serviceNameParam);
	
	ServiceDTO findById(Long serviceId);
	
	Long save(ServiceDTO serviceDTO);

	ServiceDTO update(Long serviceId, ServiceDTO serviceDTO);

	ServiceDTO deleteById(Long serviceId);

}
