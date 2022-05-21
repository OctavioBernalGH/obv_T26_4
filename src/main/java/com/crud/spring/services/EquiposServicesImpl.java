package com.crud.spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.spring.dao.EquiposDAO;
import com.crud.spring.dto.Equipos;

@Service
/**
 * Se implementan los métodos de la interfaz @EquiposServices. Se utiliza la
 * anotación @Service para indicar que esta clase pertenece a la capa de
 * servicios
 */
public class EquiposServicesImpl implements EquiposServices {

	/**
	 * Se utiliza la anotación @Autowired para inyectar las dependencias del
	 * JpaRepository heredado en EquiposDAO.
	 */
	@Autowired
	EquiposDAO equiposDAO;

	@Override
	public List<Equipos> listarEquipos() {
		// Listar todos los equipos.
		return equiposDAO.findAll();
	}

	@Override
	public Equipos buscarEquipoXIdentificador(String numSerie) {
		// Buscar equipo por ID.
		return equiposDAO.findById(numSerie).get();
	}

	@Override
	public Equipos crearNuevoEquipo(Equipos equipos) {
		// Crear un nuevo equipo.
		return equiposDAO.save(equipos);
	}

	@Override
	public Equipos modificarEquipoExistente(Equipos equipos) {
		// Modificar equipo existente.
		return equiposDAO.save(equipos);
	}

	@Override
	public void eliminarEquipoExistente(String numSerie) {
		// Eliminar equipo existente.
		equiposDAO.deleteById(numSerie);
		System.out.println("Se ha eliminado el equipo satisfactoriamente");

	}

}
