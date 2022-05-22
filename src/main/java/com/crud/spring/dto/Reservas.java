package com.crud.spring.dto;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Se define como una entidad y se relaciona con una tabla llamada reservas */
@Entity
@Table(name = "reservas")
public class Reservas{

	/** Se define que el campo Id hará referencia al atributo dni */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Se define que la columna comienzo hace referencia al atributo dni en la
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

	/**
	 * Se genera una relación many to one con la tabla investigadores mediante la
	 * columna de la clave foranea fk_investigadores.
	 */
	@ManyToOne
	@JoinColumn(name = "fk_id_investigador")
	private Investigadores investigadores;

	/**
	 * Se genera una relación many to one con la tabla equipos mediante la columna
	 * de la clave foranea fk_equipos.
	 */
	@ManyToOne
	@JoinColumn(name = "fk_id_equipo")
	private Equipos equipos;

	/** Constructor vacío */
	public Reservas() {
	}

	/**
	 * @param id
	 * @param comienzo
	 * @param fin
	 * @param investigadores
	 * @param equipos
	 */
	public Reservas(Long id, Date comienzo, Date fin, Investigadores investigadores, Equipos equipos) {
		super();
		this.id = id;
		this.comienzo = comienzo;
		this.fin = fin;
		this.investigadores = investigadores;
		this.equipos = equipos;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the comienzo
	 */
	public Date getComienzo() {
		return comienzo;
	}

	/**
	 * @param comienzo the comienzo to set
	 */
	public void setComienzo(Date comienzo) {
		this.comienzo = comienzo;
	}

	/**
	 * @return the fin
	 */
	public Date getFin() {
		return fin;
	}

	/**
	 * @param fin the fin to set
	 */
	public void setFin(Date fin) {
		this.fin = fin;
	}

	/**
	 * @return the investigadores
	 */
	public Investigadores getInvestigadores() {
		return investigadores;
	}

	/**
	 * @param investigadores the investigadores to set
	 */
	public void setInvestigadores(Investigadores investigadores) {
		this.investigadores = investigadores;
	}

	/**
	 * @return the equipos
	 */
	public Equipos getEquipos() {
		return equipos;
	}

	/**
	 * @param equipos the equipos to set
	 */
	public void setEquipos(Equipos equipos) {
		this.equipos = equipos;
	}



}
