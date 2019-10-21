package com.pvelilla.backend.hairapp.HairApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pvelilla.backend.hairapp.HairApp.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction>{

}
