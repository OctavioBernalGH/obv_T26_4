package com.crud.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crud.spring.dto.Investigadores;

@Repository
public interface InvestigadoresDAO extends JpaRepository<Investigadores, String>{

	/**
	 * Se heredan los métodos CRUD básicos de la clase JpaRepository se utiliza un
	 * String como parámetro para la entidad Investigadores.
	 */
}
