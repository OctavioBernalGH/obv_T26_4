package com.crud.spring.services;

import java.util.List;
import com.crud.spring.dto.Facultad;

/** Interfaz con la plantilla de métodos */
public interface FacultadServices {

	/** Método para listar TODAS las facultades */
	public List<Facultad> listarFacultades();

	/** Método para buscar una facultad por ID */
	public Facultad buscarFacultadXIdentificador(Long id);

	/** Método para crear una nueva facultad */
	public Facultad crearFacultad(Facultad facultad);

	/** Método para modificar una facultad ya existente */
	public Facultad modificarFacultadExistente(Facultad facultad);

	/** Método para eliminar una facultad ya existente */
	public void eliminarFacultadExistente(Long id);
}
