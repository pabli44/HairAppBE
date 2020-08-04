package com.pvelilla.backend.hairapp.HairApp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pvelilla.backend.hairapp.HairApp.config.dozer.DozerMappingBuilder;
import com.pvelilla.backend.hairapp.HairApp.config.specification.SpecificationBuilder;
import com.pvelilla.backend.hairapp.HairApp.domain.AdressDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.Adress;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.AdressRepository;
import com.pvelilla.backend.hairapp.HairApp.service.AdressService;

@Service
public class AdressServiceImpl implements AdressService{
	
	private AdressRepository adressRepository;
	private static final String NAME_DOMAIN = "Adress";
	
	
	public AdressServiceImpl(final AdressRepository adressRepository) {
		this.adressRepository = adressRepository;
	}
	
	
	@Override
	public List<AdressDTO> findAll(Optional<Long> userParam) {
		Map<String, Object> paramSpec = new HashMap<>();
		userParam.ifPresent(mapper -> paramSpec.put("userParam", userParam.get()));
		return adressRepository
				.findAll(new SpecificationBuilder<Adress>(paramSpec).conjunctionLike("[user]", "userParam").build())
				.stream().map(mapper -> new DozerMappingBuilder().map(mapper, AdressDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public AdressDTO findById(Long adressId) {
		return adressRepository.findById(adressId)
				.map(mapper -> new DozerMappingBuilder().map(mapper, AdressDTO.class))
				.orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, adressId));
	}

	@Override
	public Long save(AdressDTO adressDTO) {
		Adress adress = new DozerMappingBuilder().map(adressDTO, Adress.class);
		adressRepository.save(adress);
		return adress.getAdressId();
	}

	@Override
	public AdressDTO update(Long adressId, AdressDTO adressDTO) {
		return adressRepository.findById(adressId).map(mapper -> {
			Adress adress= new DozerMappingBuilder().map(adressDTO, Adress.class);
			adress.setAdressId(adressId);
			adressRepository.save(adress);
			return new DozerMappingBuilder().map(adress, AdressDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, adressId));
	}

	@Override
	public AdressDTO deleteById(Long adressId) {
		return adressRepository.findById(adressId).map(mapper -> {
			adressRepository.delete(mapper);
			return new DozerMappingBuilder().map(mapper, AdressDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, adressId));
	}

}
