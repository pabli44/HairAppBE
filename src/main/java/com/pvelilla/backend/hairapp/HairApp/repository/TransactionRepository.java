package com.pvelilla.backend.hairapp.HairApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pvelilla.backend.hairapp.HairApp.entities.TransactionE;

public interface TransactionRepository extends JpaRepository<TransactionE, Long>, JpaSpecificationExecutor<TransactionE>{

}
