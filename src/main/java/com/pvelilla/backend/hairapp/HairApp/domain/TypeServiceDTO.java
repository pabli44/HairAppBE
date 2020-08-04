package com.pvelilla.backend.hairapp.HairApp.domain;

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
public class TypeServiceDTO {
	
	private Long typeServiceId;
	
	private String service_name;
	
	private Long price;

}
