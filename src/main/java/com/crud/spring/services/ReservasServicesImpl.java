package com.crud.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.spring.dao.ReservasDAO;
import com.crud.spring.dto.Reservas;

@Service
/**
 * Se implementan los métodos de la interfaz @FacultadServices. Se utiliza la
 * anotación @Service para indicar que esta clase pertenece a la capa de
 * servicios
 */
public class ReservasServicesImpl implements ReservasServices {

	/**
	 * Se utiliza la anotación @Autowired para inyectar las dependencias del
	 * JpaRepository heredado en @ReservasDAO.
	 */
	@Autowired
	ReservasDAO reservasDAO;

	@Override
	public List<Reservas> listasReservas() {
		// Listar todas las reservas.
		return reservasDAO.findAll();
	}

	@Override
	public Reservas buscarReservaXIdentificador(Long id) {
		// Buscar reserva de equipo.
		return reservasDAO.findById(id).get();
	}

	@Override
	public Reservas crearNuevaReserva(Reservas reservas) {
		// Crear una nueva reserva.
		return reservasDAO.save(reservas);
	}

	@Override
	public Reservas modificarReservaExistente(Reservas reservas) {
		// Modificar una reserva existente.
		return reservasDAO.save(reservas);
	}

	@Override
	public void eliminarReservaExistente(Long id) {
		// Eliminar reserva existente
		reservasDAO.deleteById(id);
		
	}


}
