package com.crud.spring.services;

import java.util.List;
import com.crud.spring.dto.Investigadores;

/** Interfaz con la plantilla de métodos */
public interface InvestigadoresServices {

	/** Método para listar TODOS los investigadores */
	public List<Investigadores> listarInvestigadores();

	/** Método para buscar un investigador por ID */
	public Investigadores buscarInvestigadorXIdentificador(Long id);

	/** Método para crear un investigador */
	public Investigadores crearNuevoInvestigador(Investigadores investigadores);

	/** Método para modificar un investigador ya existenten */
	public Investigadores modificarInvestigadorExistente(Investigadores investigadores);

	/** Método para eliminar un investigador ya existente */
	public void eliminarInvestigadorExistente(Long id);
}
