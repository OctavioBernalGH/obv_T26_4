package com.crud.spring.services;

import java.util.List;
import com.crud.spring.dto.Reservas;

/** Interfaz con la plantilla de métodos */
public interface ReservasServices {

	/** Método para listas TODAS las reservas */
	public List<Reservas> listasReservas();

	/** Método para buscar una reserva por ID */
	public Reservas buscarReservaXIdentificador(Long id);

	/** Método para crear una nueva reserva */
	public Reservas crearNuevaReserva(Reservas reservas);

	/** Método para modificar una reserva existente */
	public Reservas modificarReservaExistente(Reservas reservas);

	/** Método para eliminar una reserva existente */
	public void eliminarReservaExistente(Long id);

}
