package datos.cuenta;

import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

public class DAOCuentasImpl implements DAOCuentas {

	private final String ID_DAO = "cuentas";
	private final String PATH = "resources/bd.json";
	

	private JSONObject _bd;
	private JSONObject _bdCuentas;
	

	DAOCuentasImpl() throws Exception {
		loadCuentas();
	}
	
	// ------- Métodos Privados --------
	private void loadCuentas(){
		try {
			InputStream in = new FileInputStream(PATH);
			_bd = new JSONObject(new JSONTokener(in));
			_bdCuentas = _bd.getJSONObject(ID_DAO);
		} catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	private boolean saveChanges() {
		try (FileWriter file = new FileWriter(PATH)) {
			_bd.put(ID_DAO, _bdCuentas);
		    file.write(_bd.toString());
		    file.flush();
		    return true;
		} catch (IOException e) {
		    e.printStackTrace();
		    return false;
		}
	}
	
	//CRUDS	
	@Override
	public boolean create(String id, JSONObject cuenta) {
		loadCuentas();
		
		_bdCuentas.put(id, cuenta);
		
		return saveChanges();
	}
	
	@Override
	public List<JSONObject> read(List<String> titularIDs) {
		loadCuentas();
		
		List<JSONObject> res = new ArrayList<>();
		
		for(String iban: titularIDs) {
			JSONObject o = _bdCuentas.getJSONObject(iban);
			res.add(o);
		}
		
		return res;
	}
	
	@Override
	public boolean update(String idEmisor, JSONObject emisor, String idReceptor, JSONObject receptor) {
		loadCuentas();
		
		_bdCuentas.put(idEmisor, emisor);
		_bdCuentas.put(idReceptor, receptor);

		return saveChanges();
	}
	
	@Override
	public boolean update(String id, JSONObject cuenta) {
		loadCuentas();
		
		_bdCuentas.put(id, cuenta);
		
		return saveChanges();
	}
	
	@Override 
	public boolean delete(String id) {
		loadCuentas();
		
		_bdCuentas.remove(id);
		
		return saveChanges();
	}

	@Override
	public JSONObject search(String iban) throws Exception {
		loadCuentas();
		
		try {
			return _bdCuentas.getJSONObject(iban);
		}
		catch(Exception e) {
			throw new Exception("La cuenta " + iban + " no existe.");
		}
	}
}