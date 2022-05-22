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
import com.crud.spring.dto.Facultad;
import com.crud.spring.services.FacultadServicesImpl;

@RestController
@RequestMapping("/api")

public class FacultadController {

	/** Utilizamos el autowired para inyectar las dependencias */
	@Autowired
	FacultadServicesImpl facultadServicesImpl;

	/** Listar todas las facultades */
	@GetMapping("/facultades")
	public List<Facultad> listarFacultades() {
		return facultadServicesImpl.listarFacultades();
	}

	/** Buscar facultad por identificador */
	@GetMapping("/facultades/{id}")
	public Facultad buscarFacultadXIdentificador(@PathVariable(name = "id") Long id) {
		return facultadServicesImpl.buscarFacultadXIdentificador(id);
	}

	/** Crear una nueva facultad */
	@PostMapping("/facultades")
	public Facultad crearNuevaFacultad(@RequestBody Facultad facultad) {
		return facultadServicesImpl.crearFacultad(facultad);
	}

	/** Modificar una facultad existente */
	@PutMapping("/facultades/{id}")
	public Facultad modificarFacultadExistente(@PathVariable(name = "id") Long id,
			@RequestBody Facultad facultad) {
		/** Instancia de la clase Facultad */
		Facultad facultad_a_modificar = new Facultad();
		Facultad modificacion = new Facultad();
		
		/** Recuperar una facultad */
		facultad_a_modificar = facultadServicesImpl.buscarFacultadXIdentificador(id);
		
		/** Modificar los valores */
		facultad_a_modificar.setId(facultad.getId());
		facultad_a_modificar.setNombre(facultad.getNombre());
		facultad_a_modificar.setEquipos(facultad.getEquipos());
		facultad_a_modificar.setInvestigadores(facultad.getInvestigadores());
		
		/** Volcar los nuevos datos */
		modificacion = facultadServicesImpl.modificarFacultadExistente(facultad_a_modificar);
		
		/** Devolver la nueva factultad ya modificada */
		return modificacion;
	}

	/** Eliminar una facultad existente */
	@DeleteMapping("/facultades/{id}")
	public void eliminarFacultad(@PathVariable(name = "id") Long id) {
		facultadServicesImpl.eliminarFacultadExistente(id);
	}

}
