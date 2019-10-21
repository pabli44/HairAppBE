package com.pvelilla.backend.hairapp.HairApp.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pvelilla.backend.hairapp.HairApp.entities.ServiceE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceDetailsDTO {
	
	private Long serviceDetailsId;

	@NotNull
	private ServiceE service;
	
	@NotNull
	private UserDTO user;
	
	@NotNull
	private Double value;
	
	@NotNull
	private Date date;
	
	@NotBlank
	private String hour;
	
	@NotNull
	private UserDTO professionalUser;
	
	@NotNull
	private TransactionDTO transaction;
	
}
