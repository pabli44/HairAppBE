package com.pvelilla.backend.hairapp.HairApp.entities;

import jakarta.persistence.*;
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
