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
		Cuenta nuevaCuenta = new Cuenta(0, "un nombre", titular);
		
		o.put("ss", nuevaCuenta.getSS());
		o.put("numCuenta", nuevaCuenta.getNumCuenta());
		o.put("dinero", nuevaCuenta.getDinero());
		o.put("titular", nuevaCuenta.getTitularID());
			
		_bdCuentas.put(nuevaCuenta.getIBAN(), o);
		
		return saveChanges();
	}
	
	@Override
	public List<Cuenta> read(Cliente titular) throws Exception {
		List<Cuenta> res = new ArrayList<>();
		
		try {
			for(String iban: titular.getCuentas()) {
				JSONObject o = (JSONObject) _bdCuentas.get(iban);
				res.add(new Cuenta(iban, o)); 	
			}
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		
		return res;
	}
	
	@Override
	public boolean update(Cuenta emisor, Cuenta receptor, float cantidad) {
		emisor.restDinero(cantidad);
		receptor.sumDinero(cantidad);
		
		JSONObject em = (JSONObject) _bdCuentas.get(emisor.getIBAN());
		JSONObject re = (JSONObject) _bdCuentas.get(receptor.getIBAN());
		
		em.put("dinero", emisor.getDinero());
		re.put("dinero", receptor.getDinero());
		
		_bdCuentas.put(emisor.getIBAN(), em);
		_bdCuentas.put(receptor.getIBAN(), re);
		
		saveChanges();
		
		return true;
	}
	
	@Override 
	public boolean delete(String iban) {
		return _bdCuentas.remove(iban) != null;
	}
	
	@Override
	public Cuenta search(String iban) throws Exception {
		Cuenta res = null;
		
		try {
			JSONObject search = (JSONObject) _bdCuentas.get(iban);
			
			res = new Cuenta(iban, search);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return res;
	}
}