package datos.cuenta;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import modelo.Cliente;
import modelo.Cuenta;

public class FIntCuentasImpl implements FIntCuentas {
	//Atributos
	private DAOCuentas dao;
	
	//Constructor
	public FIntCuentasImpl() { 
		try {
			dao = new DAOCuentasImpl();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//CRUDS
	
	@Override
	public boolean create(String id, JSONObject cuenta) {
		return dao.create(id, cuenta);
	}

	@Override
	public List<JSONObject> read(List<String> titularIDs) {
		return dao.read(titularIDs);
	}

	@Override
	public boolean update(String idEmisor, JSONObject emisor, String idReceptor, JSONObject receptor) {
		return dao.update(idEmisor, emisor, idReceptor, receptor);
	}
	
	@Override
	public boolean update(String id, JSONObject cuenta) {
		return dao.update(id, cuenta);
	}

	@Override
	public boolean delete(String id) {
		return dao.delete(id);
	}

	
}