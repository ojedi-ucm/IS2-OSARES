package datos.cita;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import modelo.Cita;
import modelo.Cliente;

public class FIntCitasImpl implements FIntCitas {
//Constantes
	
//Atributos
	private DAOCitas dao;
//Constructor
	public FIntCitasImpl() throws Exception{ dao = new DAOCitasImpl(); } //he tenido que añadir el throws
//CRUDS
	@Override
	public boolean create(String num_cita, JSONObject cita) {
		return dao.create(num_cita, cita);
	}
	@Override
	public List<JSONObject> read(List<String> clientes) {
		return dao.read(clientes);
	}
	@Override
	public boolean update(String num_cita, Date nuevaFecha, Cliente cliente) {
		return dao.update(num_cita, nuevaFecha, cliente);
	}
	@Override
	public boolean delete(String num_cita) {
		return dao.delete(num_cita);
	}
	@Override
	public JSONObject search(String num_cita)throws Exception {
		return dao.search(num_cita);
	}
}