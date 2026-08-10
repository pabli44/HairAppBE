package com.pvelilla.backend.hairapp.HairApp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pvelilla.backend.hairapp.HairApp.domain.ServiceEDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.ServiceE;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.ServiceRepository;
import com.pvelilla.backend.hairapp.HairApp.service.ServiceService;

@Service
public class ServiceServiceImpl implements ServiceService{

	private final ServiceRepository serviceRepository;
	private final ModelMapper modelMapper;
	private static final String NAME_DOMAIN = "Service";
	
	
	public ServiceServiceImpl(final ServiceRepository serviceRepository, final ModelMapper modelMapper) {
		this.serviceRepository = serviceRepository;
		this.modelMapper = modelMapper;
	}
	
	
	@Override
	public List<ServiceEDTO> findAll(Optional<Long> typeServiceParam) {
		Map<String, Object> paramSpec = new HashMap<>();
		typeServiceParam.ifPresent(mapper -> paramSpec.put("typeServiceParam", typeServiceParam.get()));
		return serviceRepository
				.findAll()
				.stream().map(mapper -> modelMapper.map(mapper, ServiceEDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public ServiceEDTO findById(Long serviceId) {
		return serviceRepository.findById(serviceId)
				.map(mapper -> modelMapper.map(mapper, ServiceEDTO.class))
				.orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, serviceId));
	}

	@Override
	public Long save(ServiceEDTO serviceDTO) {
		ServiceE serviceE = modelMapper.map(serviceDTO, ServiceE.class);
		serviceRepository.save(serviceE);
		return serviceE.getServiceId();
	}

	@Override
	public ServiceEDTO update(Long serviceId, ServiceEDTO serviceDTO) {
		return serviceRepository.findById(serviceId).map(mapper -> {
			ServiceE serviceE = modelMapper.map(serviceDTO, ServiceE.class);
			serviceE.setServiceId(serviceId);
			serviceRepository.save(serviceE);
			return modelMapper.map(serviceE, ServiceEDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, serviceId));
	}

	@Override
	public ServiceEDTO deleteById(Long serviceId) {
		return serviceRepository.findById(serviceId).map(mapper -> {
			serviceRepository.delete(mapper);
			return modelMapper.map(mapper, ServiceEDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, serviceId));
	}
	
	
}
