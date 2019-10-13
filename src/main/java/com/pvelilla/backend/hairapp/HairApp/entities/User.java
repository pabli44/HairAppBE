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
@Table(name = "usuario")
@Setter
@Getter
@NoArgsConstructor
public class User {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perfil")
	private Profile profile;
	
	@Column(name = "nombres")
	private String name;
	
	@Column(name = "apellidos")
	private String lastName;
	
	@Column(name = "nombre_usuario")
	private String userName;
	
	@Column(name = "correo")
	private String email;
	
	@Column(name = "telefono")
	private String phone;
	
}
