package datos.cita;

import java.util.List;

import org.json.JSONObject;

import modelo.Cita;

public class FIntCitasImpl implements FIntCitas {
//Constantes
	
//Atributos
	private DAOCitas dao;
//Constructor
	public FIntCitasImpl() {
		try{
			dao = new DAOCitasImpl();
		}catch(Exception e) {
			e.printStackTrace();
		}
	} 

//CRUDS
	@Override
	public boolean create(String IdCita, JSONObject cita) {
		return dao.create(IdCita, cita);
	}
	@Override
	public List<JSONObject> read(List<String> IdCitasCliente) {
		return dao.read(IdCitasCliente);
	}
	@Override
	public boolean update(String IdCita, JSONObject cita) {
		return dao.update(IdCita, cita);
	}
	@Override
	public boolean delete(String IdCita) {
		return dao.delete(IdCita);
	}
	@Override
	public Cita search(String IdCita)throws Exception {
		return dao.search(IdCita);
	}
}