package com.pvelilla.backend.hairapp.HairApp.entities;

import java.util.Date;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servicios_detalle")
@Setter
@Getter
@NoArgsConstructor
public class ServiceDetails {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serviceDetailsId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "servicio", referencedColumnName = "id")
	private ServiceE service;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private User client;
	
	@Column(name = "valor")
	private Double value;
	
	@Column(name = "fecha")
	private Date date;
	
	@Column(name = "hora")
	private String hour;
	
	@Column(name = "cantidad")
	private Long quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesional")
	private User professional;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "direccion")
	private Address address;
	
	@Column(name = "pagado")
	private String paid;
	
}
