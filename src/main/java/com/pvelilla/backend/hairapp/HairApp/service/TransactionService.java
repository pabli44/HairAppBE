package com.pvelilla.backend.hairapp.HairApp.service;

import java.util.List;
import java.util.Optional;

import com.pvelilla.backend.hairapp.HairApp.domain.TransactionEDTO;

public interface TransactionService {

	List<TransactionEDTO> findAll(Optional<Long> typeTransactionParam);
	
	TransactionEDTO findById(Long transactionId);
	
	Long save(TransactionEDTO transactionDTO);

	TransactionEDTO update(Long transactionId, TransactionEDTO transactionDTO);

	TransactionEDTO deleteById(Long transactionId);
	
}
