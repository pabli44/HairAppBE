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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pvelilla.backend.hairapp.HairApp.domain.AdressDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.ResponseMapDTO;
import com.pvelilla.backend.hairapp.HairApp.service.AdressService;

@RestController
@RequestMapping("/apiv1/adresses")
public class AdressController {
	
	private AdressService adressService;
	
	@Autowired
	public AdressController(final AdressService adressService) {
		this.adressService = adressService;
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseMapDTO save(@RequestBody @Valid AdressDTO adressDTO) {
		return new ResponseMapDTO("recordId", adressService.save(adressDTO));
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{adressId}")
	public AdressDTO deleteById(@PathVariable Long adressId) {
		return adressService.deleteById(adressId);
	}

	@CrossOrigin
	@PutMapping(value = "/{adressId}")
	public AdressDTO updateById(@PathVariable Long adressId, @RequestBody @Valid AdressDTO adressDTO) {
		return adressService.update(adressId, adressDTO);
	}

	@CrossOrigin
	@GetMapping(value = "/{adressId}")
	public AdressDTO getAdressById(@PathVariable Long adressId) {
		return adressService.findById(adressId);
	}
	
	@CrossOrigin
	@GetMapping
	public List<AdressDTO> findALlRecords(@RequestParam(name = "userParam") Optional<Long> userParam) {
		return adressService.findAll(userParam);
	}
	
}
