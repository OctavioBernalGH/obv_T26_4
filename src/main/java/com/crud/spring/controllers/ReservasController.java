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
import com.crud.spring.dto.Reservas;
import com.crud.spring.services.ReservasServicesImpl;

@RestController
@RequestMapping("/api")

public class ReservasController {

	/** Utilizamos el autowired para inyectar las dependencias */
	@Autowired
	ReservasServicesImpl reservasServicesImpl;
	
	/** Listar todas las reservas */
	@GetMapping("/reservas")
	public List<Reservas> listarReservas(){
		return reservasServicesImpl.listasReservas();
	}
	
	/** Buscar una reserva por identificador */
	@GetMapping("/reservas/{id}")
	public Reservas buscarReservaXIdentificador(@PathVariable (name = "id") Long id) {
		return reservasServicesImpl.buscarReservaXIdentificador(id);
	}
	
	/** Crear una nueva reserva */
	@PostMapping("/reservas")
	public Reservas crearNuevaReserva(@RequestBody Reservas reservas) {
		return reservasServicesImpl.crearNuevaReserva(reservas);
	}
	
	/** Modificar una reserva existente */
	@PutMapping("/reservas/{id}")
	public Reservas modificarReservaExistente(@PathVariable (name = "id") Long id , @RequestBody Reservas reservas) {
		/** Instancias de la clase Reservas */
		Reservas reserva_a_modificar = new Reservas();
		Reservas modificacion = new Reservas();
		
		/** Se recoge una reserva */
		reserva_a_modificar = reservasServicesImpl.buscarReservaXIdentificador(id);
		
		/** Se modifican sus valores */
		reserva_a_modificar.setId(reservas.getId());
		reserva_a_modificar.setComienzo(reservas.getComienzo());
		reserva_a_modificar.setFin(reservas.getFin());
		reserva_a_modificar.setInvestigadores(reservas.getInvestigadores());
		reserva_a_modificar.setEquipos(reservas.getEquipos());
		
		/** Se vuelcan los valores actualizados */
		modificacion = reservasServicesImpl.modificarReservaExistente(reserva_a_modificar);
		
		/** Se devuelve la nueva reserva */
		return modificacion;
	}
	
	/** Se elimina una reserva */
	@DeleteMapping("/eliminar/reservas/{id}")
	public void eliminarReservas(@PathVariable (name = "id") Long id) {
		reservasServicesImpl.eliminarReservaExistente(id);
	}
	
}
