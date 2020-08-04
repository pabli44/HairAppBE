package com.pvelilla.backend.hairapp.HairApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pvelilla.backend.hairapp.HairApp.entities.TypeService;

@Repository
public interface TypeServiceRepository extends JpaRepository<TypeService, Long>, JpaSpecificationExecutor<TypeService>{

}
