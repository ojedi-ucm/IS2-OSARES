package logica.cita;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

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
	public boolean create(Cita cita) {
		return dao.create(cita.getnum_cita(), cita.toJSONObject());
	}
	@Override
	public List<Cita> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Cita> readCitasCliente(Cliente cliente) {
		List<JSONObject> cuentas = dao.read(cliente.getCitas());
		List<Cita> res = new ArrayList<>();
		
		for(JSONObject j: cuentas) {
			res.add(new Cita(j));
		}
		
		return res;
	}
	@Override
	public boolean update(Cita actualizada, Date nuevaFecha) {
		return dao.update(actualizada.getnum_cita(), nuevaFecha, actualizada.getCliente());
	}
	@Override
	public boolean delete(Cita borrada) {
		return dao.delete(borrada.getnum_cita());
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