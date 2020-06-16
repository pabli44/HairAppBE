package com.pvelilla.backend.hairapp.HairApp.service;

import java.util.List;
import java.util.Optional;

import com.pvelilla.backend.hairapp.HairApp.domain.ServiceEDTO;

public interface ServiceService {
	
	List<ServiceEDTO> findAll(Optional<String> serviceNameParam);
	
	ServiceEDTO findById(Long serviceId);
	
	Long save(ServiceEDTO serviceDTO);

	ServiceEDTO update(Long serviceId, ServiceEDTO serviceDTO);

	ServiceEDTO deleteById(Long serviceId);

}
