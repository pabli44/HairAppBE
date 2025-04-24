package com.pvelilla.backend.hairapp.HairApp.controller;

import java.util.List;
import java.util.Optional;


import jakarta.validation.Valid;
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

import com.pvelilla.backend.hairapp.HairApp.domain.AddressDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.ResponseMapDTO;
import com.pvelilla.backend.hairapp.HairApp.service.AddressService;

@RestController
@RequestMapping("/apiv1/addresses")
public class AddressController {
	
	private AddressService addressService;
	
	@Autowired
	public AddressController(final AddressService addressService) {
		this.addressService = addressService;
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseMapDTO save(@RequestBody @Valid AddressDTO addressDTO) {
		return new ResponseMapDTO("recordId", addressService.save(addressDTO));
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{addresId}")
	public AddressDTO deleteById(@PathVariable Long addresId) {
		return addressService.deleteById(addresId);
	}

	@CrossOrigin
	@PutMapping(value = "/{addresId}")
	public AddressDTO updateById(@PathVariable Long addresId, @RequestBody @Valid AddressDTO addressDTO) {
		return addressService.update(addresId, addressDTO);
	}

	@CrossOrigin
	@GetMapping(value = "/{addresId}")
	public AddressDTO getAddressById(@PathVariable Long addresId) {
		return addressService.findById(addresId);
	}
	
	@CrossOrigin
	@GetMapping
	public List<AddressDTO> findALlRecords(@RequestParam(name = "userParam") Optional<Long> userParam) {
		return addressService.findAll(userParam);
	}
	
}
