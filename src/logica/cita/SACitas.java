package logica.cita;

import java.util.Date;
import java.util.List;

import modelo.Cita;
import modelo.Cliente;

public interface SACitas {
	boolean		create(Date fecha, Cliente cliente);
	List<Cita>	readAll();
	List<Cita>	readCitasCliente(Cliente cliente);
	boolean		update(Cita actualizada, Cliente cliente, Date nuevaFecha);
	boolean		delete(Cita borrada);
	boolean		completada(Cita completada);
	Cita		consultar(Date fecha, Cliente cliente);
}