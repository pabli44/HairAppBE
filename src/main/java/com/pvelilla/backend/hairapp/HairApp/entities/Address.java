package com.pvelilla.backend.hairapp.HairApp.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "direcciones")
@Setter
@Getter
@NoArgsConstructor
public class Address {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	@Column(name = "descripcion")
	private String description;
	
	@Column(name = "principal")
	private String principal;
	
	@Column(name = "usuario")
	private Long user;
	
	@Column(name = "ciudad")
	private String city;
	
}
