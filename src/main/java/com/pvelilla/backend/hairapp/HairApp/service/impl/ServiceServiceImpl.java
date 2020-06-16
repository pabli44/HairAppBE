package com.pvelilla.backend.hairapp.HairApp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pvelilla.backend.hairapp.HairApp.config.dozer.DozerMappingBuilder;
import com.pvelilla.backend.hairapp.HairApp.config.specification.SpecificationBuilder;
import com.pvelilla.backend.hairapp.HairApp.domain.ServiceEDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.ServiceE;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.ServiceRepository;
import com.pvelilla.backend.hairapp.HairApp.service.ServiceService;

@Service
public class ServiceServiceImpl implements ServiceService{

	private ServiceRepository serviceRepository;
	private static final String NAME_DOMAIN = "Service";
	
	
	public ServiceServiceImpl(final ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}
	
	
	@Override
	public List<ServiceEDTO> findAll(Optional<String> serviceNameParam) {
		Map<String, Object> paramSpec = new HashMap<>();
		serviceNameParam.ifPresent(mapper -> paramSpec.put("serviceNameParam", serviceNameParam.get()));
		return serviceRepository
				.findAll(new SpecificationBuilder<ServiceE>(paramSpec).conjunctionLike("[serviceName]", "serviceNameParam").build())
				.stream().map(mapper -> new DozerMappingBuilder().map(mapper, ServiceEDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public ServiceEDTO findById(Long serviceId) {
		return serviceRepository.findById(serviceId)
				.map(mapper -> new DozerMappingBuilder().map(mapper, ServiceEDTO.class))
				.orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, serviceId));
	}

	@Override
	public Long save(ServiceEDTO serviceDTO) {
		ServiceE serviceE = new DozerMappingBuilder().map(serviceDTO, ServiceE.class);
		serviceRepository.save(serviceE);
		return serviceE.getServiceId();
	}

	@Override
	public ServiceEDTO update(Long serviceId, ServiceEDTO serviceDTO) {
		return serviceRepository.findById(serviceId).map(mapper -> {
			ServiceE serviceE = new DozerMappingBuilder().map(serviceDTO, ServiceE.class);
			serviceE.setServiceId(serviceId);
			serviceRepository.save(serviceE);
			return new DozerMappingBuilder().map(serviceE, ServiceEDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, serviceId));
	}

	@Override
	public ServiceEDTO deleteById(Long serviceId) {
		return serviceRepository.findById(serviceId).map(mapper -> {
			serviceRepository.delete(mapper);
			return new DozerMappingBuilder().map(mapper, ServiceEDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, serviceId));
	}
	
	
}
