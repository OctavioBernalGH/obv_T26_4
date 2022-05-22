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
@RequestMapping("/api")

public class EquiposController {

	/** Utilizamos el autowired para inyectar las dependencias */
	@Autowired
	EquiposServicesImpl equiposServicesImpl;
	
	/** Listar equipos */
	@GetMapping("equipos")
	public List<Equipos> listarEquipos(){
		return equiposServicesImpl.listarEquipos();
	}
	
	/** Buscar equipo por ID */
	@GetMapping("equipos/{id}")
	public Equipos buscarEquiposXIdentificador(@PathVariable (name = "id") Long id) {
		return equiposServicesImpl.buscarEquipoXIdentificador(id);
	}
	
	/** Crear un nuevo equipo */
	@PostMapping("/equipos")
	public Equipos crearNuevoEquipo(@RequestBody Equipos equipos) {
		return equiposServicesImpl.crearNuevoEquipo(equipos);
	}
	
	/** Modificar un equipo existente */
	@PutMapping("/equipos/{id}")
	public Equipos modificarEquipoExistente(@PathVariable (name = "id") Long id, @RequestBody Equipos equipos) {
		/** Instancia de la clase equipo */
		Equipos equipo_a_modificar = new Equipos();
		Equipos modificacion = new Equipos();
		
		/** Recuperar un equipo */
		equipo_a_modificar = equiposServicesImpl.buscarEquipoXIdentificador(id);
		
		/** Introducir los nuevos valores */
		equipo_a_modificar.setId(equipos.getId());
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
	@DeleteMapping("/equipos/{id}")
	public void eliminarEquipos(@PathVariable (name = "id") Long id) {
		equiposServicesImpl.eliminarEquipoExistente(id);
	}
}
