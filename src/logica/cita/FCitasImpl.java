package logica.cita;

import java.util.List;

import modelo.Cita;
import modelo.Cliente;

public class FCitasImpl implements FCitas {
//Constantes
	
//Atributos
	private SACitas sa;
//Constructor
	public FCitasImpl() { sa = new SACitasImpl(); }
//CRRUDDS
	@Override
	public boolean create(Cita cita) {
		return sa.create(cita);
	}

	@Override
	public List<Cita> readAll() {
		return sa.readAll();
	}

	@Override
	public List<Cita> readCitasCliente(Cliente cliente) {
		return sa.readCitasCliente(cliente);
	}

	@Override
	public boolean update(Cita actualizada) {
		return sa.update(actualizada);
	}

	@Override
	public boolean delete(Cita borrada) {
		return sa.delete(borrada);
	}

	@Override
	public Cita search(String idCita) throws Exception{
		return sa.consultar(idCita);
	}
}