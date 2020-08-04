package com.pvelilla.backend.hairapp.HairApp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
	
	@NotBlank
	private String service_name;
	
	@NotBlank
	private Long price;

}
