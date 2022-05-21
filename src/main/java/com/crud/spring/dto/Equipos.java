package com.crud.spring.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** Se define como una entidad y se relaciona con una tabla llamada equipos */
@Entity
@Table(name = "equipos")
public class Equipos {

	/** Se define que el campo Id hará referencia al atributo num_serie */
	@Id
	private char num_serie;

	/**
	 * Se define que la columna nombre hace referencia al atributo nombre en la
	 * clase actual.
	 */
	@Column(name = "nombre")
	private String nombre;

}
