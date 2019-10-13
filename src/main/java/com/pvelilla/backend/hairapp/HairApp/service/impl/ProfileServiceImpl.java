package com.pvelilla.backend.hairapp.HairApp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pvelilla.backend.hairapp.HairApp.config.dozer.DozerMappingBuilder;
import com.pvelilla.backend.hairapp.HairApp.config.specification.SpecificationBuilder;
import com.pvelilla.backend.hairapp.HairApp.domain.ProfileDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.Profile;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.ProfileRepository;
import com.pvelilla.backend.hairapp.HairApp.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService{

	private ProfileRepository profileRepository;
	private static final String NAME_DOMAIN = "Profile";
	
	
	public ProfileServiceImpl(final ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}
	
	
	@Override
	public List<ProfileDTO> findAll(Optional<String> profileNameParam) {
		Map<String, Object> paramSpec = new HashMap<>();
		profileNameParam.ifPresent(mapper -> paramSpec.put("profileNameParam", profileNameParam.get()));
		return profileRepository
				.findAll(new SpecificationBuilder<Profile>(paramSpec).conjunctionLike("[profileName]", "profileNameParam").build())
				.stream().map(mapper -> new DozerMappingBuilder().map(mapper, ProfileDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public ProfileDTO findById(Long profileId) {
		return profileRepository.findById(profileId)
				.map(mapper -> new DozerMappingBuilder().map(mapper, ProfileDTO.class))
				.orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, profileId));
	}

	@Override
	public Long save(ProfileDTO profileDTO) {
		Profile profile = new DozerMappingBuilder().map(profileDTO, Profile.class);
		profileRepository.save(profile);
		return profile.getProfileId();
	}

	@Override
	public ProfileDTO update(Long profileId, ProfileDTO profileDTO) {
		return profileRepository.findById(profileId).map(mapper -> {
			Profile profile = new DozerMappingBuilder().map(profileDTO, Profile.class);
			profile.setProfileId(profileId);
			profileRepository.save(profile);
			return new DozerMappingBuilder().map(profile, ProfileDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, profileId));
	}

	@Override
	public ProfileDTO deleteById(Long profileId) {
		return profileRepository.findById(profileId).map(mapper -> {
			profileRepository.delete(mapper);
			return new DozerMappingBuilder().map(mapper, ProfileDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, profileId));
	}
	
}
