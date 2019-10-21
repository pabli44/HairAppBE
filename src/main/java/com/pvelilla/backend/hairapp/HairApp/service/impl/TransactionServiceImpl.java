package com.pvelilla.backend.hairapp.HairApp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pvelilla.backend.hairapp.HairApp.config.dozer.DozerMappingBuilder;
import com.pvelilla.backend.hairapp.HairApp.config.specification.SpecificationBuilder;
import com.pvelilla.backend.hairapp.HairApp.domain.TransactionDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.Transaction;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.TransactionRepository;
import com.pvelilla.backend.hairapp.HairApp.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	private TransactionRepository transactionRepository;
	private static final String NAME_DOMAIN = "Transaction";
	
	
	public TransactionServiceImpl(final TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	
	@Override
	public List<TransactionDTO> findAll(Optional<Long> typeTransactionParam) {
		Map<String, Object> paramSpec = new HashMap<>();
		typeTransactionParam.ifPresent(mapper -> paramSpec.put("typeTransactionParam", typeTransactionParam.get()));
		return transactionRepository
				.findAll(new SpecificationBuilder<Transaction>(paramSpec).conjunctionEquals("[typeTransaction]", "typeTransactionParam").build())
				.stream().map(mapper -> new DozerMappingBuilder().map(mapper, TransactionDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public TransactionDTO findById(Long transactionId) {
		return transactionRepository.findById(transactionId)
				.map(mapper -> new DozerMappingBuilder().map(mapper, TransactionDTO.class))
				.orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, transactionId));
	}

	@Override
	public Long save(TransactionDTO transactionDTO) {
		Transaction transaction = new DozerMappingBuilder().map(transactionDTO, Transaction.class);
		transactionRepository.save(transaction);
		return transaction.getTransactionId();
	}

	@Override
	public TransactionDTO update(Long transactionId, TransactionDTO transactionDTO) {
		return transactionRepository.findById(transactionId).map(mapper -> {
			Transaction transaction = new DozerMappingBuilder().map(transactionDTO, Transaction.class);
			transaction.setTransactionId(transactionId);
			transactionRepository.save(transaction);
			return new DozerMappingBuilder().map(transaction, TransactionDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, transactionId));
	}

	@Override
	public TransactionDTO deleteById(Long transactionId) {
		return transactionRepository.findById(transactionId).map(mapper -> {
			transactionRepository.delete(mapper);
			return new DozerMappingBuilder().map(mapper, TransactionDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, transactionId));
	}
	
}
