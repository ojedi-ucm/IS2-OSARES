package datos.cuenta;

import java.util.ArrayList;
import java.util.List;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONArray;

import modelo.Cliente;
import modelo.Cuenta;

public class DAOCuentasImpl implements DAOCuentas {
//Constantes
	private final String ID_DAO = "cuentas";
	private final String PATH = "resources/bd.json";
//Atributos
	private JSONObject _bdCuentas;
	
//Constructor
	DAOCuentasImpl() throws Exception {
		loadCuentas();
	}
	
	private void loadCuentas() throws Exception {
		try {
			_bdCuentas = (JSONObject) new JSONObject(new FileReader(PATH)).get(ID_DAO);
		} catch(Exception e ) {
			throw new Exception(e);
		}
	}
	
	private boolean saveChanges() {
		try (FileWriter file = new FileWriter(PATH)) {
		    file.write(_bdCuentas.toString());
		    file.flush();
		    return true;
		} catch (IOException e) {
		    e.printStackTrace();
		    return false;
		}
	}
	
//CRUDS	
	@Override
	public boolean create(Cliente titular) {
		JSONObject o = new JSONObject();
		Cuenta nuevaCuenta = new Cuenta(0, titular);
		
		o.put("ss", nuevaCuenta.getSS());
		o.put("numCuenta", nuevaCuenta.getNumCuenta());
		o.put("dinero", nuevaCuenta.getDinero());
		o.put("titular", nuevaCuenta.getTitularID());
			
		_bdCuentas.put(nuevaCuenta.getIBAN(), o);
		
		return saveChanges();
	}
	
	@Override
	public List<Cuenta> read(Cliente titular) {
		List<Cuenta> res = new ArrayList<>();
		
		for(String iban: titular.getCuentas()) {
			JSONObject o = (JSONObject) _bdCuentas.get(iban);
			res.add(new Cuenta(iban, o));
		}
		
		return res;
	}
	
	@Override
	public boolean update(Cuenta emisor, Cuenta receptor, float cantidad) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override 
	public boolean delete(String iban) {
		return _bdCuentas.remove(iban) != null;
	}
	
	@Override
	public Cuenta search(String iban) {
		JSONObject search = (JSONObject) _bdCuentas.get(iban);
		
		Cuenta res = new Cuenta(iban, search);
		
		return res;
	}
}