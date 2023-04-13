package logica.cita;

import java.util.Date;
import java.util.List;

import datos.cita.FIntCitas;
import datos.cita.FIntCitasImpl;
import modelo.Cita;
import modelo.Cliente;

public class SACitasImpl implements SACitas {
//Constantes

//Atributos
	private FIntCitas dao;
//Constructor
	SACitasImpl() { dao = new FIntCitasImpl(); }
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