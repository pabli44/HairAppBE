package com.pvelilla.backend.hairapp.HairApp.domain;


import jakarta.validation.constraints.NotBlank;
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
public class ProfileDTO {
	
	private Long profileId;
	
	@NotBlank
	private String profileName;

}
