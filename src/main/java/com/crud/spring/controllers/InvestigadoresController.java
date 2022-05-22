package com.crud.spring.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crud.spring.dto.Investigadores;
import com.crud.spring.services.InvestigadoresServicesImpl;

@RestController
@RequestMapping("/api")

public class InvestigadoresController {

	/** Utilizamos el autowired para inyectar las dependencias */
	@Autowired
	InvestigadoresServicesImpl investigadoresServicesImpl;

	/** Listar todos los investigadores */
	@GetMapping("/investigadores")
	public List<Investigadores> listarInvestigadores() {
		return investigadoresServicesImpl.listarInvestigadores();
	}

	/** Buscar un investigador por identificador */
	@GetMapping("/investigadores/{id}")
	public Investigadores buscarInvestigadorXIdentificador(@PathVariable(name = "id") Long id) {
		return investigadoresServicesImpl.buscarInvestigadorXIdentificador(id);
	}

	/** Crear un nuevo investigador */
	@PostMapping("/investigadores")
	public Investigadores crearNuevoInvestigador(@RequestBody Investigadores investigadores) {
		return investigadoresServicesImpl.crearNuevoInvestigador(investigadores);
	}

	/** Modificar un investigador existente */
	@PutMapping("/investigadores/{id}")
	public Investigadores modificarInvestigadorExistente(@PathVariable(name = "id") Long id,
			@RequestBody Investigadores investigadores) {
		/** Instancia de un investigador */
		Investigadores investigador_a_modificar = new Investigadores();
		Investigadores modificacion = new Investigadores();
		
		/** Recuperar un investigador para modificar */
		investigador_a_modificar = investigadoresServicesImpl.buscarInvestigadorXIdentificador(id);
		
		/** Actualizar valores */
		investigador_a_modificar.setId(investigadores.getId());
		investigador_a_modificar.setDni(investigadores.getDni());
		investigador_a_modificar.setFacultad(investigadores.getFacultad());
		investigador_a_modificar.setReservas(investigadores.getReservas());
		investigador_a_modificar.setNomApels(investigadores.getNomApels());
		/** Volcar valores actualizados */
		modificacion = investigadoresServicesImpl.modificarInvestigadorExistente(investigador_a_modificar);
		
		/** Devolver el nuevo investigador */
		return modificacion;
	}

	/** Eliminar un investigador existente */
	@DeleteMapping("/investigadores/{id}")
	public void Investigadores(@PathVariable(name = "id") Long id) {
		investigadoresServicesImpl.eliminarInvestigadorExistente(id);
	}
}
