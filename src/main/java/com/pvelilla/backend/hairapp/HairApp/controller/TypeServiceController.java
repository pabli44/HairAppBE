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

import com.pvelilla.backend.hairapp.HairApp.domain.ResponseMapDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.TypeServiceDTO;
import com.pvelilla.backend.hairapp.HairApp.service.TypeServiceService;

@RestController
@RequestMapping("/apiv1/typeservices")
public class TypeServiceController {
	
	private TypeServiceService typeServiceService;
	
	@Autowired
	public TypeServiceController(final TypeServiceService typeServiceService) {
		this.typeServiceService = typeServiceService;
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseMapDTO save(@RequestBody @Valid TypeServiceDTO typeServiceDTO) {
		return new ResponseMapDTO("recordId", typeServiceService.save(typeServiceDTO));
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{typeServiceId}")
	public TypeServiceDTO deleteById(@PathVariable Long typeServiceId) {
		return typeServiceService.deleteById(typeServiceId);
	}

	@CrossOrigin
	@PutMapping(value = "/{typeServiceId}")
	public TypeServiceDTO updateById(@PathVariable Long typeServiceId, @RequestBody @Valid TypeServiceDTO typeServiceDTO) {
		return typeServiceService.update(typeServiceId, typeServiceDTO);
	}

	@CrossOrigin
	@GetMapping(value = "/{typeServiceId}")
	public TypeServiceDTO getTypeServiceById(@PathVariable Long typeServiceId) {
		return typeServiceService.findById(typeServiceId);
	}
	
	@CrossOrigin
	@GetMapping
	public List<TypeServiceDTO> findALlRecords(@RequestParam(name = "priceParam") Optional<Long> priceParam) {
		return typeServiceService.findAll(priceParam);
	}
}

