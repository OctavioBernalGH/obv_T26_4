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

/**
 * Se define como una entidad y se relaciona con una tabla llamada
 * investigadores
 */
@Entity
@Table(name = "investigadores")
public class Investigadores {

	/** Se define que el campo Id hará referencia al atributo dni */
	@Id
	private String dni;

	/**
	 * Se define que la columna nombre_apels hace referencia al atributo nombreApels
	 * en la clase actual.
	 */
	@Column(name = "nombre_apels")
	private String nombreApels;

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
	@JoinColumn(name = "dni")
	private List<Reservas> reservas;

	/** Constructor vacío */
	public Investigadores() {
	}

	/**
	 * Constructor completo incluyendo las relaciones entre tablas.
	 * 
	 * @param dni
	 * @param nombreApels
	 * @param facultad
	 * @param reservas
	 */
	public Investigadores(String dni, String nombreApels, Facultad facultad, List<Reservas> reservas) {
		super();
		this.dni = dni;
		this.nombreApels = nombreApels;
		this.facultad = facultad;
		this.reservas = reservas;
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
	 * @return the nombreApels
	 */
	public String getNombreApels() {
		return nombreApels;
	}

	/**
	 * @param nombreApels the nombreApels to set
	 */
	public void setNombreApels(String nombreApels) {
		this.nombreApels = nombreApels;
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
	 * En el getter relacional del One To Many se introduce la anotación JsonIgnore
	 * para evitar la recursividad. Mediante esta anotación evitamos posibles
	 * bucles.
	 * 
	 * @return the reservas
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
