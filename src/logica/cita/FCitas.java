package logica.cita;

import java.util.List;
import modelo.Cita;
import modelo.Cliente;

public interface FCitas {
	public boolean create(Cita cita);
	public List<Cita> readAll();
	public List<Cita> readCitasCliente(Cliente cliente);
	public boolean update(Cita actualizada);
	public boolean delete(Cita borrada);
	public Cita search(String num_cita) throws Exception;
}