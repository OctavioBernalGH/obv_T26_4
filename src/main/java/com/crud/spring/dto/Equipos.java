package com.crud.spring.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

/** Se define como una entidad y se relaciona con una tabla llamada equipos */
@Entity
@Table(name = "equipos")
public class Equipos {

	/** Se define que el campo Id hará referencia al atributo numSerie */
	@Id
	private char numSerie;

	/**
	 * Se define que la columna nombre hace referencia al atributo nombre en la
	 * clase actual.
	 */
	@Column(name = "nombre")
	private String nombre;

	/**
	 * Se genera una relación many to one con la tabla facultad mediante la columna
	 * de la clave foranea fk_facultad.
	 */
	@ManyToOne
	@JoinColumn(name = "fk_facultad")
	private Facultad facultad;

	/**
	 * Se genera una relación one to many con la tabla reserva teniendo como
	 * referencia el identificador de esta clase.
	 */
	@OneToMany
	@JoinColumn(name = "numSerie")
	private List<Reservas> reservas;

	/** Constructor vacío */
	public Equipos() {
	}

	/**
	 * Constructor con todos los atributos propios y con el atributo relacional de
	 * one to many.
	 * 
	 * @param numSerie
	 * @param nombre
	 * @param reservas
	 */
	public Equipos(char numSerie, String nombre, Facultad facultad, List<Reservas> reservas) {
		this.numSerie = numSerie;
		this.nombre = nombre;
		this.facultad = facultad;
		this.reservas = reservas;
	}

	/**
	 * @return the numSerie
	 */
	public char getNumSerie() {
		return numSerie;
	}

	/**
	 * @param numSerie the numSerie to set
	 */
	public void setNumSerie(char numSerie) {
		this.numSerie = numSerie;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the facultad
	 */
	public Facultad getFacultad() {
		return facultad;
	}

	/**
	 * @param facultad the facultad to set
	 */
	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	/**
	 * Mediante JsonIgnore eliminamos la recursividad para evitar generar bucles
	 * infinitos
	 * 
	 * @return
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Reservas")
	public List<Reservas> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	public void setReservas(List<Reservas> reservas) {
		this.reservas = reservas;
	}

}
