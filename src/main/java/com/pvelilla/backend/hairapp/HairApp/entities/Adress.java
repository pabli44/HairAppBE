package com.pvelilla.backend.hairapp.HairApp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "direcciones")
@Setter
@Getter
@NoArgsConstructor
public class Adress {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adressId;
	
	@Column(name = "descripcion")
	private String description;
	
	@Column(name = "principal")
	private String principal;
	
	@Column(name = "usuario")
	private Long user;
	
	@Column(name = "ciudad")
	private String city;
	
}
