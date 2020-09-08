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

import com.pvelilla.backend.hairapp.HairApp.domain.ProfileDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.ResponseMapDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.ServiceEDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.TypeService;
import com.pvelilla.backend.hairapp.HairApp.service.ProfileService;
import com.pvelilla.backend.hairapp.HairApp.service.ServiceService;

@RestController
@RequestMapping("/apiv1/services")
public class ServiceController {
	
	private ServiceService serviceService;
	
	@Autowired
	public ServiceController(final ServiceService serviceService) {
		this.serviceService = serviceService;
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseMapDTO save(@RequestBody @Valid ServiceEDTO serviceDTO) {
		return new ResponseMapDTO("recordId", serviceService.save(serviceDTO));
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{serviceId}")
	public ServiceEDTO deleteById(@PathVariable Long profileId) {
		return serviceService.deleteById(profileId);
	}

	@CrossOrigin
	@PutMapping(value = "/{serviceId}")
	public ServiceEDTO updateById(@PathVariable Long serviceId, @RequestBody @Valid ServiceEDTO serviceDTO) {
		return serviceService.update(serviceId, serviceDTO);
	}

	@CrossOrigin
	@GetMapping(value = "/{serviceId}")
	public ServiceEDTO getServiceById(@PathVariable Long serviceId) {
		return serviceService.findById(serviceId);
	}
	
	@CrossOrigin
	@GetMapping
	public List<ServiceEDTO> findALlRecords(@RequestParam(name = "typeServiceParam") Optional<Long> typeServiceParam) {
		return serviceService.findAll(typeServiceParam);
	}

}
