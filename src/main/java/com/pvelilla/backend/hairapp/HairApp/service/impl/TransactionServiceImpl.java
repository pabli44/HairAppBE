package com.pvelilla.backend.hairapp.HairApp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pvelilla.backend.hairapp.HairApp.domain.TransactionEDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.TransactionE;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.TransactionRepository;
import com.pvelilla.backend.hairapp.HairApp.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	private final TransactionRepository transactionRepository;
	private final ModelMapper modelMapper;
	private static final String NAME_DOMAIN = "Transaction";
	
	
	public TransactionServiceImpl(final TransactionRepository transactionRepository, final ModelMapper modelMapper) {
		this.transactionRepository = transactionRepository;
		this.modelMapper = modelMapper;
	}
	
	
	@Override
	public List<TransactionEDTO> findAll(Optional<Long> typeTransactionParam) {
		Map<String, Object> paramSpec = new HashMap<>();
		typeTransactionParam.ifPresent(mapper -> paramSpec.put("typeTransactionParam", typeTransactionParam.get()));
		return transactionRepository
				.findAll()
				.stream().map(mapper -> modelMapper.map(mapper, TransactionEDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public TransactionEDTO findById(Long transactionId) {
		return transactionRepository.findById(transactionId)
				.map(mapper -> modelMapper.map(mapper, TransactionEDTO.class))
				.orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, transactionId));
	}

	@Override
	public Long save(TransactionEDTO transactionDTO) {
		TransactionE transaction = modelMapper.map(transactionDTO, TransactionE.class);
		transactionRepository.save(transaction);
		return transaction.getTransactionId();
	}

	@Override
	public TransactionEDTO update(Long transactionId, TransactionEDTO transactionDTO) {
		return transactionRepository.findById(transactionId).map(mapper -> {
			TransactionE transaction = modelMapper.map(transactionDTO, TransactionE.class);
			transaction.setTransactionId(transactionId);
			transactionRepository.save(transaction);
			return modelMapper.map(transaction, TransactionEDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, transactionId));
	}

	@Override
	public TransactionEDTO deleteById(Long transactionId) {
		return transactionRepository.findById(transactionId).map(mapper -> {
			transactionRepository.delete(mapper);
			return modelMapper.map(mapper, TransactionEDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, transactionId));
	}
	
}
