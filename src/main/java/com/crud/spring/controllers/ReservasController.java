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
@RequestMapping("/aplicacion")

public class ReservasController {

	/** Utilizamos el autowired para inyectar las dependencias */
	@Autowired
	ReservasServicesImpl reservasServicesImpl;
	
	/** Listar todas las reservas */
	@GetMapping("/listar/reservas")
	public List<Reservas> listarReservas(){
		return reservasServicesImpl.listasReservas();
	}
	
	/** Buscar una reserva por identificador */
	@GetMapping("/buscar/reserva/dni/{id}")
	public Reservas buscarReservaXIdentificador(@PathVariable (name = "id") String dni) {
		return reservasServicesImpl.buscarReservaXIdentificadorInvestigadores(dni);
	}
	
	/** Crear una nueva reserva */
	@PostMapping("/crear/reserva")
	public Reservas crearNuevaReserva(@RequestBody Reservas reservas) {
		return reservasServicesImpl.crearNuevaReserva(reservas);
	}
	
	/** Modificar una reserva existente */
	@PutMapping("/modificar/reserva/{id}")
	public Reservas modificarReservaExistente(@PathVariable (name = "id") String dni , @RequestBody Reservas reservas) {
		/** Instancias de la clase Reservas */
		Reservas reserva_a_modificar = new Reservas();
		Reservas modificacion = new Reservas();
		
		/** Se recoge una reserva */
		reserva_a_modificar = reservasServicesImpl.buscarReservaXIdentificadorInvestigadores(dni);
		
		/** Se modifican sus valores */
		reserva_a_modificar.setDni(reservas.getDni());
		reserva_a_modificar.setNumSerie(reservas.getNumSerie());
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
	public void eliminarReservas(@PathVariable (name = "id") String dni) {
		reservasServicesImpl.eliminarReservaExistenteInvestigadores(dni);
	}
	
}
