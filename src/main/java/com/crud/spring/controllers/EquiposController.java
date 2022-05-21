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
import com.crud.spring.dto.Equipos;
import com.crud.spring.services.EquiposServicesImpl;

@RestController
@RequestMapping("/aplicacion")

public class EquiposController {

	/** Utilizamos el autowired para inyectar las dependencias */
	@Autowired
	EquiposServicesImpl equiposServicesImpl;
	
	/** Listar equipos */
	@GetMapping("/listar/equipos")
	public List<Equipos> listarEquipos(){
		return equiposServicesImpl.listarEquipos();
	}
	
	/** Buscar equipo por ID */
	@GetMapping("/buscar/equipo/{id}")
	public Equipos buscarEquiposXIdentificador(@PathVariable (name = "id") String numSerie) {
		return equiposServicesImpl.buscarEquipoXIdentificador(numSerie);
	}
	
	/** Crear un nuevo equipo */
	@PostMapping("/crear/equipo")
	public Equipos crearNuevoEquipo(@RequestBody Equipos equipos) {
		return equiposServicesImpl.crearNuevoEquipo(equipos);
	}
	
	/** Modificar un equipo existente */
	@PutMapping("/modificar/equipo/{id}")
	public Equipos modificarEquipoExistente(@PathVariable (name = "id") String numSerie, @RequestBody Equipos equipos) {
		/** Instancia de la clase equipo */
		Equipos equipo_a_modificar = new Equipos();
		Equipos modificacion = new Equipos();
		
		/** Recuperar un equipo */
		equipo_a_modificar = equiposServicesImpl.buscarEquipoXIdentificador(numSerie);
		
		/** Introducir los nuevos valores */
		equipo_a_modificar.setNumSerie(equipos.getNumSerie());
		equipo_a_modificar.setNombre(equipos.getNombre());
		equipo_a_modificar.setFacultad(equipos.getFacultad());
		equipo_a_modificar.setReservas(equipos.getReservas());
		
		/** Volcamos los datos nuevos */
		modificacion = equiposServicesImpl.modificarEquipoExistente(equipo_a_modificar);
		
		/** Devolvemos el nuevo equipo */
		return modificacion;
	}
	
	/** Eliminar un equipo */
	@DeleteMapping("/eliminar/equipo/{id}")
	public void eliminarEquipos(@PathVariable (name = "id") String numSerie) {
		equiposServicesImpl.eliminarEquipoExistente(numSerie);
	}
}
