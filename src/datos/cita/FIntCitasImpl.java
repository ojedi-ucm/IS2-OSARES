package datos.cita;

import java.util.Date;
import java.util.List;

import modelo.Cita;
import modelo.Cliente;

public class FIntCitasImpl implements FIntCitas {
//Constantes
	
//Atributos
	private DAOCitas dao;
//Constructor
	public FIntCitasImpl() { dao = new DAOCitasImpl(); }
//CRUDS
	@Override
	public boolean create(Date fecha, Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Cita> read(List<Cliente> clientes) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean update(Cliente cliente, Date nuevaFecha, boolean isCompletada) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Cita borrada) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Cita search(Date dia) {
		// TODO Auto-generated method stub
		return null;
	}
}