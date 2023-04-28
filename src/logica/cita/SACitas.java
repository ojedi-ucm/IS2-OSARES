package logica.cita;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import modelo.Cita;
import modelo.Cliente;

public interface SACitas {
	public boolean create(Cita cita);
	List<Cita>	readAll();
	public List<Cita> readCitasCliente(Cliente cliente);
	public boolean update(Cita actualizada, Date nuevaFecha);
	public boolean delete(Cita borrada);
	boolean		completada(Cita completada);
	Cita		consultar(Date fecha, Cliente cliente);
}