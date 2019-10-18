package com.pvelilla.backend.hairapp.HairApp.domain;

import javax.validation.constraints.NotBlank;

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
public class ServiceDTO {
	
	private Long serviceId;
	
	@NotBlank
	private String serviceName;
	
	@NotBlank
	private String state;

}
