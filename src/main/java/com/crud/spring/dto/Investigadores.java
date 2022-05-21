package com.crud.spring.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** Se define como una entidad y se relaciona con una tabla llamada investigadores */
@Entity
@Table(name = "investigadores")
public class Investigadores {

	/** Se define que el campo Id hará referencia al atributo dni */
	@Id
	private String dni;

	/**
	 * Se define que la columna nombre_apels hace referencia al atributo
	 * nombre_apels en la clase actual.
	 */
	@Column(name = "nombre_apels")
	private String nombre_apels;

}
