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
@Table(name = "servicios")
@Setter
@Getter
@NoArgsConstructor
public class ServiceE {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serviceId;
	
	@Column(name = "nombre_servicio")
	private String serviceName;
	
	@Column(name = "estado")
	private String state;
	
}
