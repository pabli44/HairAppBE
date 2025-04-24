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
public class UserDTO {
	
	private Long userId;
	
	@NotNull
	private ProfileDTO profile;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String phone;

}
