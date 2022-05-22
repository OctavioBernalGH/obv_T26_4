package com.crud.spring.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Se define que la columna nombre hace referencia al atributo nombre en la
	 * clase actual.
	 */
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "numSerie")
	private String numSerie;
	
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
	@JoinColumn(name = "id")
	private List<Reservas> reservas;

	/** Constructor vacío */
	public Equipos() {
	}



	/**
	 * @param id
	 * @param nombre
	 * @param numSerie
	 * @param facultad
	 * @param reservas
	 */
	public Equipos(Long id, String nombre, String numSerie, Facultad facultad, List<Reservas> reservas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numSerie = numSerie;
		this.facultad = facultad;
		this.reservas = reservas;
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
	
	

}
