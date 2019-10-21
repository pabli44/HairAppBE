package com.pvelilla.backend.hairapp.HairApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pvelilla.backend.hairapp.HairApp.entities.ServiceDetails;

@Repository
public interface ServiceDetailsRepository extends JpaRepository<ServiceDetails, Long>, JpaSpecificationExecutor<ServiceDetails> {

}
