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

/**
 * Se define como una entidad y se relaciona con una tabla llamada
 * investigadores
 */
@Entity
@Table(name = "investigadores")
public class Investigadores {

	/** Se define que el campo Id hará referencia al atributo dni */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Se define que la columna nombre_apels hace referencia al atributo nombreApels
	 * en la clase actual.
	 */

	@Column(name = "nom_apels")
	private String nomApels;

	/**
	 * Se define que la columna nombre_apels hace referencia al atributo dni en la
	 * clase actual.
	 */

	@Column(name = "dni")
	private String dni;
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
	 * @param id
	 * @param nomApels
	 * @param dni
	 * @param facultad
	 * @param reservas
	 */
	public Investigadores(Long id, String nomApels, String dni, Facultad facultad, List<Reservas> reservas) {
		super();
		this.id = id;
		this.nomApels = nomApels;
		this.dni = dni;
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
	 * @return the nomApels
	 */
	public String getNomApels() {
		return nomApels;
	}


	/**
	 * @param nomApels the nomApels to set
	 */
	public void setNomApels(String nomApels) {
		this.nomApels = nomApels;
	}
	
	

}
