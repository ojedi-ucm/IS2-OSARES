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
//Atributos
	private JSONObject _bdCuentas;
	
//Constructor
	DAOCuentasImpl() throws Exception {
		try {
			_bdCuentas = (JSONObject) new JSONObject(new FileReader("resources/bd.json")).get(ID_DAO);
		} catch(Exception e ) {
			throw new Exception(e);
		}
		
	}
	
	private boolean saveChanges() {
		try (FileWriter file = new FileWriter("resources/bd.json")) {
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
	public boolean create(ArrayList<Cliente> titulares) {
		JSONObject o = new JSONObject();
		Cuenta nuevaCuenta = new Cuenta(0, titulares);
		
		o.append("titulares", titulares);
		o.put("IBAN", nuevaCuenta.getIBAN());
		o.put("dinero", nuevaCuenta.getDinero());
		
		_bdCuentas.put(Long.toString(nuevaCuenta.getIBAN()), o);
		
		return saveChanges();
	}
	
	@Override
	public List<Cuenta> read(ArrayList<Cliente> titulares) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean update(Cliente emisor, Cliente receptor, float cantidad) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Cuenta search(long IBAN) {
		JSONObject search = (JSONObject) _bdCuentas.get(Long.toString(IBAN));
		JSONArray jsonTitulares = search.getJSONArray("titulares");
		
		List<Cliente> titulares = new ArrayList<>();
		
		for(Object c: jsonTitulares)
			titulares.add((Cliente) c);
			
		
		Cuenta res = new Cuenta(search.getFloat("dinero"), titulares);
		
		return res;
	}
}