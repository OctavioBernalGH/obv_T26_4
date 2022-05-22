package com.crud.spring.services;

import java.util.List;
import com.crud.spring.dto.Equipos;

/** Interfaz con la plantilla de métodos */
public interface EquiposServices {

	/** Método para listar TODOS los equipos */
	public List<Equipos> listarEquipos();

	/** Método para buscar un equipo por ID */
	public Equipos buscarEquipoXIdentificador(Long id);

	/** Método para crear un nuevo equipo */
	public Equipos crearNuevoEquipo(Equipos equipos);

	/** Método para modificar un equipo ya existente */
	public Equipos modificarEquipoExistente(Equipos equipos);

	/** Método para eliminar un equipo ya existente */
	public void eliminarEquipoExistente(Long id);

}
