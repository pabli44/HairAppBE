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
import com.pvelilla.backend.hairapp.HairApp.domain.TransactionDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.UserDTO;
import com.pvelilla.backend.hairapp.HairApp.service.TransactionService;
import com.pvelilla.backend.hairapp.HairApp.service.UserService;

@RestController
@RequestMapping("/apiv1/transactions")
public class TransactionController {

	private TransactionService transactionService;
	
	@Autowired
	public TransactionController(final TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseMapDTO save(@RequestBody @Valid TransactionDTO transactionDTO) {
		return new ResponseMapDTO("recordId", transactionService.save(transactionDTO));
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{transactionId}")
	public TransactionDTO deleteById(@PathVariable Long transactionId) {
		return transactionService.deleteById(transactionId);
	}

	@CrossOrigin
	@PutMapping(value = "/{transactionId}")
	public TransactionDTO updateById(@PathVariable Long transactionId, @RequestBody @Valid TransactionDTO transactionDTO) {
		return transactionService.update(transactionId, transactionDTO);
	}

	@CrossOrigin
	@GetMapping(value = "/{transactionId}")
	public TransactionDTO getTransactionById(@PathVariable Long transactionId) {
		return transactionService.findById(transactionId);
	}
	
	@CrossOrigin
	@GetMapping
	public List<TransactionDTO> findALlRecords(@RequestParam(name = "typeTransactionParam") Optional<Long> typeTransactionParam) {
		return transactionService.findAll(typeTransactionParam);
	}
	
}
