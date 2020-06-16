package com.pvelilla.backend.hairapp.HairApp.domain;

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
public class TransactionEDTO {
	
	private Long transactionId;
	
	@NotNull
	private Long typeTransaction;

	@NotNull
	private Long state; 
		
	
}
