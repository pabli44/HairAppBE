package com.pvelilla.backend.hairapp.HairApp.service;

import java.util.List;
import java.util.Optional;

import com.pvelilla.backend.hairapp.HairApp.domain.TransactionDTO;

public interface TransactionService {

	List<TransactionDTO> findAll(Optional<Long> typeTransactionParam);
	
	TransactionDTO findById(Long transactionId);
	
	Long save(TransactionDTO transactionDTO);

	TransactionDTO update(Long transactionId, TransactionDTO transactionDTO);

	TransactionDTO deleteById(Long transactionId);
	
}
