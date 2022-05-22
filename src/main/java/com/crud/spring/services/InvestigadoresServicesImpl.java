package com.crud.spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.spring.dao.InvestigadoresDAO;
import com.crud.spring.dto.Investigadores;

@Service
/**
 * Se implementan los métodos de la interfaz @InvestigadoresServices. Se utiliza
 * la anotación @Service para indicar que esta clase pertenece a la capa de
 * servicios
 */
public class InvestigadoresServicesImpl implements InvestigadoresServices {

	/**
	 * Se utiliza la anotación @Autowired para inyectar las dependencias del
	 * JpaRepository heredado en @InvestigadoresDAO.
	 */
	@Autowired
	InvestigadoresDAO investigadoresDAO;

	@Override
	public List<Investigadores> listarInvestigadores() {
		// Listar todos los investigadores.
		return investigadoresDAO.findAll();
	}

	@Override
	public Investigadores buscarInvestigadorXIdentificador(Long id) {
		// Buscar investigador por identificador.
		return investigadoresDAO.findById(id).get();
	}

	@Override
	public Investigadores crearNuevoInvestigador(Investigadores investigadores) {
		// Crear un nuevo investigador.
		return investigadoresDAO.save(investigadores);
	}

	@Override
	public Investigadores modificarInvestigadorExistente(Investigadores investigadores) {
		// Modificar un investigador existente.
		return investigadoresDAO.save(investigadores);
	}

	@Override
	public void eliminarInvestigadorExistente(Long id) {
		// Eliminar un investigador existente.
		investigadoresDAO.deleteById(id);
		System.out.println("Se ha eliminado el investigador satisfactoriamente");

	}

}
