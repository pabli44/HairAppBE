package com.pvelilla.backend.hairapp.HairApp.service;

import java.util.List;
import java.util.Optional;

import com.pvelilla.backend.hairapp.HairApp.domain.ProfileDTO;

public interface ProfileService {

	List<ProfileDTO> findAll(Optional<String> profileNameParam);
	
	ProfileDTO findById(Long profileId);
	
	Long save(ProfileDTO profileDTO);

	ProfileDTO update(Long profileId, ProfileDTO profileDTO);

	ProfileDTO deleteById(Long profileId);
	
}
