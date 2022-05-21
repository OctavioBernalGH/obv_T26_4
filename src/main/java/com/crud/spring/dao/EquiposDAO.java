package com.crud.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.spring.dto.Equipos;

public interface EquiposDAO extends JpaRepository<Equipos, String> {

	/**
	 * Se heredan los métodos CRUD básicos de la clase JpaRepository se utiliza un
	 * String como parámetro para la entidad Equipos.
	 */
}
