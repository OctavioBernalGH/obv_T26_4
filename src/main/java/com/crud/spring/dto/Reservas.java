package com.crud.spring.dto;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** Se define como una entidad y se relaciona con una tabla llamada reservas */
@Entity
@Table(name = "reservas")
public class Reservas {

	/** Se define que el campo Id hará referencia al atributo dni */
	@Id
	private String dni;

	/** Se define que el campo Id hará referencia al atributo num_serie */
	@Id
	private String num_serie;

	/**
	 * Se define que la columna comienzo hace referencia al atributo comienzo en la
	 * clase actual.
	 */
	@Column(name = "comienzo")
	private Date comienzo;

	/**
	 * Se define que la columna fin hace referencia al atributo fin en la clase
	 * actual.
	 */
	@Column(name = "fin")
	private Date fin;

}
