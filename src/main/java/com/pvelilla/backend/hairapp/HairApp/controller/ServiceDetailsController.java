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

import com.pvelilla.backend.hairapp.HairApp.domain.ResponseMapDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.ServiceDetailsDTO;
import com.pvelilla.backend.hairapp.HairApp.service.ServiceDetailsService;

@RestController
@RequestMapping("/apiv1/servicedetails")
public class ServiceDetailsController {

	private ServiceDetailsService serviceDetailsService;
	
	@Autowired
	public ServiceDetailsController(final ServiceDetailsService serviceDetailsService) {
		this.serviceDetailsService = serviceDetailsService;
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseMapDTO save(@RequestBody @Valid ServiceDetailsDTO serviceDetailsDTO) {
		return new ResponseMapDTO("recordId", serviceDetailsService.save(serviceDetailsDTO));
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{serviceDetailsId}")
	public ServiceDetailsDTO deleteById(@PathVariable Long serviceDetailsId) {
		return serviceDetailsService.deleteById(serviceDetailsId);
	}

	@CrossOrigin
	@PutMapping(value = "/{serviceDetailsId}")
	public ServiceDetailsDTO updateById(@PathVariable Long serviceDetailsId, @RequestBody @Valid ServiceDetailsDTO serviceDetailsDTO) {
		return serviceDetailsService.update(serviceDetailsId, serviceDetailsDTO);
	}

	@CrossOrigin
	@GetMapping(value = "/{serviceDetailsId}")
	public ServiceDetailsDTO getServiceDetailsById(@PathVariable Long serviceDetailsId) {
		return serviceDetailsService.findById(serviceDetailsId);
	}
	
	@CrossOrigin
	@GetMapping
	public List<ServiceDetailsDTO> findALlRecords(@RequestParam(name = "serviceParam") Optional<Long> serviceParam) {
		return serviceDetailsService.findAll(serviceParam);
	}
	
	@CrossOrigin
	@GetMapping(value = "/client")
	public List<ServiceDetailsDTO> findALlRecordsByClient(@RequestParam(name = "clientParam") Optional<Long> clientParam) {
		return serviceDetailsService.findAllByClient(clientParam);
	}
	
	@CrossOrigin
	@GetMapping(value = "/professional")
	public List<ServiceDetailsDTO> findALlRecordsByProfessional(@RequestParam(name = "professionalParam") Optional<Long> professionalParam) {
		return serviceDetailsService.findAllByProfessional(professionalParam);
	}
	
}
