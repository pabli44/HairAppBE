package com.pvelilla.backend.hairapp.HairApp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pvelilla.backend.hairapp.HairApp.domain.ProfileDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.Profile;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.ProfileRepository;
import com.pvelilla.backend.hairapp.HairApp.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService{

	private final ProfileRepository profileRepository;
	private final ModelMapper modelMapper;
	private static final String NAME_DOMAIN = "Profile";
	
	
	public ProfileServiceImpl(final ProfileRepository profileRepository, final ModelMapper modelMapper) {
		this.profileRepository = profileRepository;
		this.modelMapper = modelMapper;
	}
	
	
	@Override
	public List<ProfileDTO> findAll(Optional<String> profileNameParam) {
		if (profileNameParam.isPresent()) {
			return profileRepository
					.findAll(byProfileName(profileNameParam.get()))
					.stream().map(mapper -> modelMapper.map(mapper, ProfileDTO.class))
					.collect(Collectors.toList());
		}
		return profileRepository
				.findAll()
				.stream().map(mapper -> modelMapper.map(mapper, ProfileDTO.class))
				.collect(Collectors.toList());
	}

	private Specification<Profile> byProfileName(String profileName) {
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.equal(criteriaBuilder.lower(root.get("profileName")), profileName.toLowerCase());
	}
	
	@Override
	public ProfileDTO findById(Long profileId) {
		return profileRepository.findById(profileId)
				.map(mapper -> modelMapper.map(mapper, ProfileDTO.class))
				.orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, profileId));
	}

	@Override
	public Long save(ProfileDTO profileDTO) {
		Profile profile = modelMapper.map(profileDTO, Profile.class);
		profileRepository.save(profile);
		return profile.getProfileId();
	}

	@Override
	public ProfileDTO update(Long profileId, ProfileDTO profileDTO) {
		return profileRepository.findById(profileId).map(mapper -> {
			Profile profile = modelMapper.map(profileDTO, Profile.class);
			profile.setProfileId(profileId);
			profileRepository.save(profile);
			return modelMapper.map(profile, ProfileDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, profileId));
	}

	@Override
	public ProfileDTO deleteById(Long profileId) {
		return profileRepository.findById(profileId).map(mapper -> {
			profileRepository.delete(mapper);
			return modelMapper.map(mapper, ProfileDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, profileId));
	}
	
}
