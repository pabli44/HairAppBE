package com.pvelilla.backend.hairapp.HairApp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pvelilla.backend.hairapp.HairApp.domain.TypeServiceDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.TypeService;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.TypeServiceRepository;
import com.pvelilla.backend.hairapp.HairApp.service.TypeServiceService;

@Service
public class TypeServiceServiceImpl implements TypeServiceService{

	private final TypeServiceRepository typeServiceRepository;
	private final ModelMapper modelMapper;
	private static final String NAME_DOMAIN = "TypeService";
	
	
	public TypeServiceServiceImpl(final TypeServiceRepository typeServiceRepository, final ModelMapper modelMapper) {
		this.typeServiceRepository = typeServiceRepository;
		this.modelMapper = modelMapper;
	}
	
	
	@Override
	public List<TypeServiceDTO> findAll(Optional<Long> priceParam) {
		Map<String, Object> paramSpec = new HashMap<>();
		priceParam.ifPresent(mapper -> paramSpec.put("priceParam", priceParam.get()));
		return typeServiceRepository
				.findAll()
				.stream().map(mapper -> modelMapper.map(mapper, TypeServiceDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public TypeServiceDTO findById(Long typeServiceId) {
		return typeServiceRepository.findById(typeServiceId)
				.map(mapper -> modelMapper.map(mapper, TypeServiceDTO.class))
				.orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, typeServiceId));
	}

	@Override
	public Long save(TypeServiceDTO typeServiceDTO) {
		TypeService typeService = modelMapper.map(typeServiceDTO, TypeService.class);
		typeServiceRepository.save(typeService);
		return typeService.getTypeServiceId();
	}

	@Override
	public TypeServiceDTO update(Long typeServiceId, TypeServiceDTO typeServiceDTO) {
		return typeServiceRepository.findById(typeServiceId).map(mapper -> {
			TypeService typeService = modelMapper.map(typeServiceDTO, TypeService.class);
			typeService.setTypeServiceId(typeServiceId);
			typeServiceRepository.save(typeService);
			return modelMapper.map(typeService, TypeServiceDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, typeServiceId));
	}

	@Override
	public TypeServiceDTO deleteById(Long typeServiceId) {
		return typeServiceRepository.findById(typeServiceId).map(mapper -> {
			typeServiceRepository.delete(mapper);
			return modelMapper.map(mapper, TypeServiceDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, typeServiceId));
	}
	
	
}
