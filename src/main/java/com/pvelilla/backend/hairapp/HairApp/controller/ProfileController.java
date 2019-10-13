package com.pvelilla.backend.hairapp.HairApp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pvelilla.backend.hairapp.HairApp.domain.ProfileDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.ResponseMapDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.UserDTO;
import com.pvelilla.backend.hairapp.HairApp.service.ProfileService;
import com.pvelilla.backend.hairapp.HairApp.service.UserService;

@RestController
public class ProfileController {
	
	private ProfileService profileService;
	
	@Autowired
	public ProfileController(final ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseMapDTO save(@RequestBody @Valid ProfileDTO profileDTO) {
		return new ResponseMapDTO("recordId", profileService.save(profileDTO));
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{profileId}")
	public ProfileDTO deleteById(@PathVariable Long profileId) {
		return profileService.deleteById(profileId);
	}

	@CrossOrigin
	@PutMapping(value = "/{profileId}")
	public ProfileDTO updateById(@PathVariable Long profileId, @RequestBody @Valid ProfileDTO profileDTO) {
		return profileService.update(profileId, profileDTO);
	}

	@CrossOrigin
	@GetMapping(value = "/{profileId}")
	public ProfileDTO getProfileById(@PathVariable Long profileId) {
		return profileService.findById(profileId);
	}
	
	@CrossOrigin
	@GetMapping
	public List<ProfileDTO> findALlRecords(@RequestParam(name = "profileNameParam") Optional<String> profileNameParam) {
		return profileService.findAll(profileNameParam);
	}
}
