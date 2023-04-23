package logica.cuenta;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import datos.cuenta.FIntCuentas;
import datos.cuenta.FIntCuentasImpl;
import modelo.Cliente;
import modelo.Cuenta;

public class SACuentasImpl implements SACuentas {

	private FIntCuentas dao;

	SACuentasImpl() { 
		dao = new FIntCuentasImpl(); 
	}
	
	//CRUD
	@Override
	public boolean create(Cuenta cuenta) {
		JSONObject o = cuenta.getJSON();
		
		return dao.create(cuenta.getIBAN(), o);
	}
	
	@Override
	public List<Cuenta> read(Cliente titular) throws Exception {
		List<JSONObject> get = dao.read(titular.getCuentas());
		List<Cuenta> res = new ArrayList<>();
		
		for(JSONObject o: get)
			res.add(new Cuenta(o));
		
		return res;
	}
	
	
	
	@Override
	public boolean update(Cuenta emisor, Cuenta receptor, float cantidad) {
		emisor.modificarDinero(-cantidad);
		receptor.modificarDinero(cantidad);
		
		return dao.update(emisor.getIBAN(), emisor.getJSON(), receptor.getIBAN(), receptor.getJSON());
	}
	
	@Override
	public boolean update(Cuenta emisor, String ibanReceptor, float cantidad) {
		Cuenta receptor = new Cuenta(dao.search(ibanReceptor));
		
		emisor.modificarDinero(-cantidad);
		receptor.modificarDinero(cantidad);
		
		return dao.update(emisor.getIBAN(), emisor.getJSON(), receptor.getIBAN(), receptor.getJSON());
	}
	
	@Override
	public boolean update(Cuenta cuenta) {
		return dao.update(cuenta.getIBAN(), cuenta.getJSON());
	}
	
	@Override
	public boolean update(Cuenta cuenta, float cantidad) {
		cuenta.modificarDinero(cantidad);
		
		return dao.update(cuenta.getIBAN(), cuenta.getJSON());
	}
	
	@Override
	public boolean delete(Cuenta cuenta) {
		return dao.delete(cuenta.getIBAN());
	}
}