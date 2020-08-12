package com.pvelilla.backend.hairapp.HairApp.entities;

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
@Table(name = "servicios")
@Setter
@Getter
@NoArgsConstructor
public class ServiceE {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serviceId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_servicio")
	private TypeService typeService;
	
	@Column(name = "estado")
	private String state;
	
}
