package logica.cuenta;

import java.time.Instant;
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
	public boolean update(Cuenta cuenta) { // Update básico de una cuenta
		return dao.update(cuenta.getIBAN(), cuenta.getJSON());
	}
	
	@Override
	public boolean update(Cuenta emisor, Cuenta receptor, float cantidad) throws Exception { // Update para transferencias cuenta->cuenta
		long now = Instant.now().getEpochSecond();
		String id = now + emisor.getIBAN();
		
		emisor.modificarDinero(-cantidad);
		receptor.modificarDinero(cantidad);
		
		emisor.nuevaTransaccion(id, emisor.getIBAN(), receptor.getIBAN(), cantidad);
		receptor.nuevaTransaccion(id, emisor.getIBAN(), receptor.getIBAN(), cantidad);
		
		return dao.update(emisor.getIBAN(), emisor.getJSON(), receptor.getIBAN(), receptor.getJSON());
	}
	
	@Override
	public boolean update(Cuenta emisor, String ibanReceptor, float cantidad) throws Exception { // Update para transferencias cuenta->iban
		Cuenta receptor = new Cuenta(dao.search(ibanReceptor));
		
		long now = Instant.now().getEpochSecond();
		String id = now + emisor.getIBAN();
		
		emisor.modificarDinero(-cantidad);
		receptor.modificarDinero(cantidad);
		
		emisor.nuevaTransaccion(id, emisor.getIBAN(), receptor.getIBAN(), cantidad);
		receptor.nuevaTransaccion(id, emisor.getIBAN(), receptor.getIBAN(), cantidad);
		
		return dao.update(emisor.getIBAN(), emisor.getJSON(), receptor.getIBAN(), receptor.getJSON());
	}
	
	@Override
	public boolean update(Cuenta cuenta, float cantidad) throws Exception { // Update para depósitos y retiradas (desde el banco)
		cuenta.modificarDinero(cantidad);
		
		long now = Instant.now().getEpochSecond();
		
		if(cantidad > 0) {
			String id = now + "BancoDeposito";
			cuenta.nuevaTransaccion(id, "Banco (Depósito)", cuenta.getIBAN(), cantidad);
		} else {
			String id = now + "BancoRetirada";
			cuenta.nuevaTransaccion(id, cuenta.getIBAN(), "Banco (Retirada)", cantidad);
		}
		
		return dao.update(cuenta.getIBAN(), cuenta.getJSON());
	}
	
	@Override
	public boolean delete(Cuenta cuenta) {
		return dao.delete(cuenta.getIBAN());
	}

	@Override
	public Cuenta search(String iban) {
		Cuenta res = null;
		
		try {
			res = new Cuenta(dao.search(iban));
		} catch(Exception e) {}
				
		return res;
	}
}