package control;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import logica.cuenta.FCuentas;
import logica.cuenta.FCuentasImpl;
import modelo.Cliente;
import modelo.Cuenta;
import vista.observers.CuentasObserver;

public class ControlCuenta {
	
	private Map<String, Cuenta> _cuentas;
	private List<CuentasObserver> _observers;
	private float _dineroTotal;
	private Cliente _titular;
	
	private FCuentas _fCuentas;
	
	public ControlCuenta(Cliente titular) {
		_titular = titular;
		reset();
	}
	
	// ------ Private Methods -------
	
	private void reset() {
		_dineroTotal = 0;
		
		_fCuentas = new FCuentasImpl();
		_observers = new ArrayList<>();
		_cuentas = new HashMap<>();
		
		try {
			for(Cuenta c: _fCuentas.read(_titular)) {
				_cuentas.put(c.getIBAN(), c);
				_dineroTotal += c.getDinero();
			}
				
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private void updateObs() {
		for(CuentasObserver o: _observers) {
			o.updateCuentas(_cuentas);
			o.updateDinero(_dineroTotal);
		}
	}
	
	private void updateDineroTotal() {
		_dineroTotal = 0;
		for(Cuenta c: _cuentas.values()) {
			_dineroTotal += c.getDinero();
		}
	}
	
	// ------ Public Methods -------
	
	public void addObserver(CuentasObserver obs) {
		_observers.add(obs);
		obs.updateCuentas(_cuentas);
		obs.updateDinero(_dineroTotal);
	}
	
	public void addDinero(String iban, float cantidad) throws Exception {
		_dineroTotal += cantidad;
		_fCuentas.update(_cuentas.get(iban), cantidad);
		updateObs();
	}
	
	public void retDinero(String iban, float cantidad) throws Exception {
		_fCuentas.update(_cuentas.get(iban), -cantidad);
		_dineroTotal -= cantidad;
		updateObs();
	}
	
	public void transferirDinero(String emisor, String receptor, float cantidad) throws Exception {
		Cuenta emi = _cuentas.get(emisor);
		Cuenta rec = _cuentas.get(receptor);
		
		if(rec == null)
			_fCuentas.update(emi, receptor, cantidad);
		else
			_fCuentas.update(emi, rec, cantidad);
		
		updateDineroTotal();
		updateObs();
	}
	
	public void abrirCuenta(String nombre) throws Exception {
		if(nombre.isBlank())
			throw new Exception("El nombre de la cuenta no puede ser vacío.");
		
		Cuenta nueva = new Cuenta(0, nombre, _titular);
		_cuentas.put(nueva.getIBAN(), nueva);
		
		_titular.addCuenta(nueva.getIBAN());
		
		_fCuentas.create(nueva);
		
		updateObs();
	}
	
	public void cerrarCuenta(String ibanCerrar, String ibanTrans) throws Exception {
		Cuenta cerrar = _cuentas.get(ibanCerrar);
		Cuenta trans = _cuentas.get(ibanTrans);
		
		_fCuentas.update(cerrar, trans, cerrar.getDinero());
		
		_cuentas.remove(ibanCerrar);
	
		_fCuentas.delete(cerrar);
		
		updateObs();
	}
	
	public List<Cuenta> getCuentas() {
		List<Cuenta> list = new ArrayList<>();
		
		for(Cuenta c: _cuentas.values())
			list.add(c);
		
		return list;
	}
	
	public float getDineroTotal() {
		return _dineroTotal;
	}
	
	public String getTitularID() {
		return _titular.getID();
	}
}