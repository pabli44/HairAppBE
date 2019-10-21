package com.pvelilla.backend.hairapp.HairApp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pvelilla.backend.hairapp.HairApp.config.dozer.DozerMappingBuilder;
import com.pvelilla.backend.hairapp.HairApp.config.specification.SpecificationBuilder;
import com.pvelilla.backend.hairapp.HairApp.domain.ServiceDetailsDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.ServiceDetails;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.ServiceDetailsRepository;
import com.pvelilla.backend.hairapp.HairApp.service.ServiceDetailsService;

public class ServiceDetailsServiceImpl implements ServiceDetailsService{

	private ServiceDetailsRepository serviceDetailsRepository;
	private static final String NAME_DOMAIN = "ServiceDetails";
	
	
	public ServiceDetailsServiceImpl(final ServiceDetailsRepository serviceDetailsRepository) {
		this.serviceDetailsRepository = serviceDetailsRepository;
	}
	
	
	@Override
	public List<ServiceDetailsDTO> findAll(Optional<Long> serviceParam) {
		Map<String, Object> paramSpec = new HashMap<>();
		serviceParam.ifPresent(mapper -> paramSpec.put("serviceParam", serviceParam.get()));
		return serviceDetailsRepository
				.findAll(new SpecificationBuilder<ServiceDetails>(paramSpec).conjunctionLike("[service][ServiceId]", "serviceParam").build())
				.stream().map(mapper -> new DozerMappingBuilder().map(mapper, ServiceDetailsDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public ServiceDetailsDTO findById(Long serviceDetailsId) {
		return serviceDetailsRepository.findById(serviceDetailsId)
				.map(mapper -> new DozerMappingBuilder().map(mapper, ServiceDetailsDTO.class))
				.orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, serviceDetailsId));
	}

	@Override
	public Long save(ServiceDetailsDTO serviceDetailsDTO) {
		ServiceDetails serviceDetails = new DozerMappingBuilder().map(serviceDetailsDTO, ServiceDetails.class);
		serviceDetailsRepository.save(serviceDetails);
		return serviceDetails.getServiceDetailsId();
	}

	@Override
	public ServiceDetailsDTO update(Long serviceDetailsId, ServiceDetailsDTO serviceDetailsDTO) {
		return serviceDetailsRepository.findById(serviceDetailsId).map(mapper -> {
			ServiceDetails serviceDetails = new DozerMappingBuilder().map(serviceDetailsDTO, ServiceDetails.class);
			serviceDetails.setServiceDetailsId(serviceDetailsId);
			serviceDetailsRepository.save(serviceDetails);
			return new DozerMappingBuilder().map(serviceDetails, ServiceDetailsDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, serviceDetailsId));
	}

	@Override
	public ServiceDetailsDTO deleteById(Long serviceDetailsId) {
		return serviceDetailsRepository.findById(serviceDetailsId).map(mapper -> {
			serviceDetailsRepository.delete(mapper);
			return new DozerMappingBuilder().map(mapper, ServiceDetailsDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, serviceDetailsId));
	}
	
}
