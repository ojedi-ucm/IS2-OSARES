package logica.cita;

import java.util.Date;
import java.util.List;

import modelo.Cita;
import modelo.Cliente;

public class FCitasImpl implements FCitas {
//Constantes
	
//Atributos
	private SACitas sa;
//Constructor
	public FCitasImpl()throws Exception { sa = new SACitasImpl(); }
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
	public boolean update(Cita actualizada, Date nuevaFecha) {
		return sa.update(actualizada, nuevaFecha);
	}

	@Override
	public boolean delete(Cita borrada) {
		return sa.delete(borrada);
	}

	@Override
	public boolean completada(Cita completada) {
		return sa.completada(completada);
	}

	@Override
	public Cita consultar(Date fecha, Cliente cliente) {
		return sa.consultar(fecha, cliente);
	}
}