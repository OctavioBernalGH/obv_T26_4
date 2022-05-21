package com.crud.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crud.spring.dto.Facultad;

@Repository
public interface FacultadDAO extends JpaRepository<Facultad, Integer>{

	/**
	 * Se heredan los métodos CRUD básicos de la clase JpaRepository se utiliza un
	 * Integer como parámetro para la entidad Facultad.
	 */
}
