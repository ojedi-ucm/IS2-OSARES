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
	public FCitasImpl() { sa = new SACitasImpl(); }
//CRRUDDS
	@Override
	public boolean create(Date fecha, Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cita> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cita> readCitasCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Cita actualizada, Cliente cliente, Date nuevaFecha) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cita borrada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean completada(Cita completada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cita consultar(Date fecha, Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}
}