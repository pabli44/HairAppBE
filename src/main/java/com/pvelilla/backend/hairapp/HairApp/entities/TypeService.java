package com.pvelilla.backend.hairapp.HairApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tipo_servicio")
@Setter
@Getter
@NoArgsConstructor
public class TypeService {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typeServiceId;
	
	@Column(name = "nombre_servicio")
	private String serviceName;
	
	@Column(name = "precio")
	private Long price;

}
