package com.crud.spring.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** Se define como una entidad y se relaciona con una tabla llamada facultad */
@Entity
@Table(name = "facultad")

public class Facultad {

	/**
	 * Se define el campo ID y se mapea con el atributo código indicando que es la
	 * clave primaria de la entidad. El generationType indica la forma de
	 * incremento, si en el mysql viene definido como auto_incremental se definirá
	 * como incremental automático aquí.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	/**
	 * Se define que la columna nombre hace referencia al atributo nombre en la
	 * clase actual.
	 */
	@Column(name = "nombre")
	private String nombre;
}
