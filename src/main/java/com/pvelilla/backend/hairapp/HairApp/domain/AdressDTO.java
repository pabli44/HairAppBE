package com.pvelilla.backend.hairapp.HairApp.domain;

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
public class AdressDTO {
	private Long adressId;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String principal;
	
	@NotNull
	private Long user;
	
	@NotBlank
	private String city;
	
	
}
