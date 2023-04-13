package datos.cita;

import java.util.Date;
import java.util.List;

import modelo.Cita;
import modelo.Cliente;

public interface DAOCitas {
	public boolean		create(Date fecha, Cliente cliente);
	public List<Cita>	read(List<Cliente> clientes);
	public boolean		update(Cliente cliente,
							   Date nuevaFecha,
							   boolean isCompletada);
	public boolean		delete(Cita borrada);
	public Cita			search(Date dia);
}