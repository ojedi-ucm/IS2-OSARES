package logica.cita;

import java.util.List;

import modelo.Cita;
import modelo.Cliente;

public interface SACitas {
	public boolean create(Cita cita);
	List<Cita>	readAll();
	public List<Cita> readCitasCliente(Cliente cliente);
	public boolean update(Cita actualizada);
	public boolean delete(Cita borrada);
	Cita consultar (String IdCita) throws Exception;
}