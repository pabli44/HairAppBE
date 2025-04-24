package com.pvelilla.backend.hairapp.HairApp.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "perfil")
@Setter
@Getter
@NoArgsConstructor
public class Profile {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long profileId;
	
	@Column(name = "nombre_perfil")
	private String profileName;
	
}
