package com.pvelilla.backend.hairapp.HairApp.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@JoinColumn(name = "servicio")
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transaccion")
	private TransactionE transaction;
	
	@Column(name = "cantidad")
	private Long quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesional")
	private User professional;
	
}
