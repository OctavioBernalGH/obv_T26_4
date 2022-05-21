package com.crud.spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.spring.dao.FacultadDAO;
import com.crud.spring.dto.Facultad;

@Service
/**
 * Se implementan los métodos de la interfaz @FacultadServices. Se utiliza la
 * anotación @Service para indicar que esta clase pertenece a la capa de
 * servicios
 */
public class FacultadServicesImpl implements FacultadServices {

	/**
	 * Se utiliza la anotación @Autowired para inyectar las dependencias del
	 * JpaRepository heredado en @FacultadDAO.
	 */
	@Autowired
	FacultadDAO facultadDAO;

	@Override
	public List<Facultad> listarFacultades() {
		// Listar todas las facultades.
		return facultadDAO.findAll();
	}

	@Override
	public Facultad buscarFacultadXIdentificador(Integer codigo) {
		// Buscar facultad por identificador.
		return facultadDAO.findById(codigo).get();
	}

	@Override
	public Facultad crearFacultad(Facultad facultad) {
		// Crear una nueva facultad.
		return facultadDAO.save(facultad);
	}

	@Override
	public Facultad modificarFacultadExistente(Facultad facultad) {
		// Modificar una facultad existente.
		return facultadDAO.save(facultad);
	}

	@Override
	public void eliminarFacultadExistente(Integer codigo) {
		// Eliminar una facultad existente.
		facultadDAO.deleteById(codigo);
		System.out.println("Se ha eliminado la facultad satisfactoriamente");

	}

}
