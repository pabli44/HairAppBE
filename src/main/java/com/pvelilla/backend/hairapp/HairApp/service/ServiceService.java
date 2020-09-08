package com.pvelilla.backend.hairapp.HairApp.service;

import java.util.List;
import java.util.Optional;

import com.pvelilla.backend.hairapp.HairApp.domain.ServiceEDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.TypeService;

public interface ServiceService {
	
	List<ServiceEDTO> findAll(Optional<Long> typeServiceParam);
	
	ServiceEDTO findById(Long serviceId);
	
	Long save(ServiceEDTO serviceDTO);

	ServiceEDTO update(Long serviceId, ServiceEDTO serviceDTO);

	ServiceEDTO deleteById(Long serviceId);

}
