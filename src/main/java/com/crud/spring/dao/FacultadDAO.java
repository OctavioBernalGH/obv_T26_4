package com.crud.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.spring.dto.Facultad;

public interface FacultadDAO extends JpaRepository<Facultad, Integer>{

	/**
	 * Se heredan los métodos CRUD básicos de la clase JpaRepository se utiliza un
	 * Integer como parámetro para la entidad Facultad.
	 */
}
