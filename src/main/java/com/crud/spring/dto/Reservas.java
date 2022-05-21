package com.crud.spring.dto;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Se define como una entidad y se relaciona con una tabla llamada reservas */
@Entity
@Table(name = "reservas")
public class Reservas {

	/** Se define que el campo Id hará referencia al atributo dni */
	@Id
	@Column(name = "dni")
	private String dni;

	/** Se define que el campo Id hará referencia al atributo numSerie */
	@Id
	@Column(name = "numSerie")
	private String numSerie;

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

	/**
	 * Se genera una relación many to one con la tabla investigadores mediante la
	 * columna de la clave foranea fk_investigadores.
	 */
	@ManyToOne
	@JoinColumn(name = "fk_investigadores")
	private Investigadores investigadores;

	/**
	 * Se genera una relación many to one con la tabla equipos mediante la columna
	 * de la clave foranea fk_equipos.
	 */
	@ManyToOne
	@JoinColumn(name = "fk_equipos")
	private Equipos equipos;

	/** Constructor vacío */
	public Reservas() {
	}

	/**
	 * Constructor con todos los atributos de clase y con las relaciones
	 * 
	 * @param dni
	 * @param numSerie
	 * @param comienzo
	 * @param fin
	 * @param investigadores
	 * @param equipos
	 */
	public Reservas(String dni, String numSerie, Date comienzo, Date fin, Investigadores investigadores,
			Equipos equipos) {
		super();
		this.dni = dni;
		this.numSerie = numSerie;
		this.comienzo = comienzo;
		this.fin = fin;
		this.investigadores = investigadores;
		this.equipos = equipos;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the numSerie
	 */
	public String getNumSerie() {
		return numSerie;
	}

	/**
	 * @param numSerie the numSerie to set
	 */
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
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
