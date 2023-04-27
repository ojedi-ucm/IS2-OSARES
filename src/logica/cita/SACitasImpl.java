package logica.cita;

import java.util.ArrayList;
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
	SACitasImpl() throws Exception{ dao = new FIntCitasImpl(); }
//CRRUDDS
	@Override
	public boolean create(Date fecha, Cliente cliente) {
		return dao.create(fecha, cliente);
	}
	@Override
	public List<Cita> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Cita> readCitasCliente(Cliente cliente) {
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(cliente);
		return dao.read(clientes);
	}
	@Override
	public boolean update(Cita actualizada, Cliente cliente, Date nuevaFecha) {
		return dao.update(cliente, nuevaFecha, false);
	}
	@Override
	public boolean delete(Cita borrada) {
		return dao.update(borrada.getCliente(), borrada.getFecha(), false);
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