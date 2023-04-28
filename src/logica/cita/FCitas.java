package logica.cita;

import java.util.Date;
import java.util.List;
import modelo.Cita;
import modelo.Cliente;

public interface FCitas {
	public boolean create(Cita cita);
	public List<Cita>	readAll();
	public List<Cita>	readCitasCliente(Cliente cliente);
	public boolean update(Cita actualizada, Date nuevaFecha);
	public boolean		delete(Cita borrada);
	public boolean		completada(Cita completada);
	public Cita			consultar(Date fecha, Cliente cliente);
}