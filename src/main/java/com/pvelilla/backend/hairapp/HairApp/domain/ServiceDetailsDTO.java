package com.pvelilla.backend.hairapp.HairApp.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	private ServiceEDTO service;
	
	@NotNull
	private UserDTO client;
	
	@NotNull
	private Double value;
	
	@NotNull
	private Date date;
	
	@NotBlank
	private String hour;
	
	@NotNull
	private TransactionEDTO transaction;
	
	@NotNull
	private Long quantity;
	
	@NotNull
	private UserDTO professional;
	
}
