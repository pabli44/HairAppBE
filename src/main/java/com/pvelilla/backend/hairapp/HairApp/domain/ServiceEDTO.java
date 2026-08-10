package com.pvelilla.backend.hairapp.HairApp.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ServiceEDTO {
	
	private Long serviceId;
	
	@NotNull
	private TypeServiceDTO typeService;
	
	@NotBlank
	private String state;

}
