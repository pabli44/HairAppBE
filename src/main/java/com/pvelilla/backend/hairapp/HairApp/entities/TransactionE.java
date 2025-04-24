package com.pvelilla.backend.hairapp.HairApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "transacciones")
@Setter
@Getter
@NoArgsConstructor
public class TransactionE {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	
	@Column(name = "tipo_transaccion")
	private Long typeTransaction;
	
	@Column(name = "estado")
	private Long state; 
	
	
	
}
