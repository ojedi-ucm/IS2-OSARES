package control;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import logica.cuenta.FCuentas;
import logica.cuenta.FCuentasImpl;
import modelo.Cliente;
import modelo.Cuenta;
import vista.observers.CuentasObserver;

public class ControlCuenta {
	
	private final String SS_CODE = "ES21";
	
	private Map<String, Cuenta> _cuentas;
	private List<CuentasObserver> _observers;
	private float _dineroTotal;
	private Cliente _titular;
	
	private List<JSONObject> _transaccionesSel;
	
	private FCuentas _fCuentas;
	
	private ControlCliente _ctrlCliente;
	
	public ControlCuenta(Cliente titular, ControlCliente ctrlCliente) {
		_titular = titular;
		_ctrlCliente = ctrlCliente;
		reset();
	}
	
	// ------ Private Methods -------
	
	private void reset() {
		_dineroTotal = 0;
		
		_fCuentas = new FCuentasImpl();
		_observers = new ArrayList<>();
		_cuentas = new HashMap<>();
		_transaccionesSel = new ArrayList<>();
		
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
			o.updateTransacciones(_transaccionesSel);
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
		
		Random rand;
		BigInteger numCuenta;
		
		do { // Comprobamos si el numero de cuenta generado ya existe (si existe -> generamos otro)
			rand = new Random();
			numCuenta = new BigInteger(20 * 5, rand);
		} while(_fCuentas.search(SS_CODE + numCuenta) != null);
		
		
		Cuenta nueva = new Cuenta(nombre, SS_CODE, numCuenta, _titular);
		
		_fCuentas.create(nueva);
		
		_cuentas.put(nueva.getIBAN(), nueva);
		
		_titular.addCuenta(nueva.getIBAN());
		
		_ctrlCliente.actualizarCliente(_titular);
		
		updateObs();
	}
	
	public void cerrarCuenta(String ibanCerrar, String ibanTrans) throws Exception {
		Cuenta cerrar = _cuentas.get(ibanCerrar);
		Cuenta trans = _cuentas.get(ibanTrans);
		
		_fCuentas.update(cerrar, trans, cerrar.getDinero());
		
		_cuentas.remove(ibanCerrar);
	
		_fCuentas.delete(cerrar);
		
		_titular.removeCuenta(ibanCerrar);
		
		_ctrlCliente.actualizarCliente(_titular);
		
		updateObs();
	}
	
	public void cerrarCuenta(String ibanCerrar) throws Exception {
		Cuenta cerrar = _cuentas.get(ibanCerrar);
		
		_cuentas.remove(ibanCerrar);
	
		_fCuentas.delete(cerrar);
		
		_titular.removeCuenta(ibanCerrar);
		
		_ctrlCliente.actualizarCliente(_titular);
		
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
		return _titular.getId();
	}
	
	public Cliente getTitular() {
		return _titular;
	}
	
	public void updateSelTransacciones(String iban) {
		_transaccionesSel.clear();
		
		Cuenta c = _cuentas.get(iban);
		
		for(JSONObject transaccion: c.getTransacciones())
			_transaccionesSel.add(transaccion);
		
		for(CuentasObserver o: _observers) {
			o.updateTransacciones(_transaccionesSel);
		}
	}
	
	public String getNombre(String iban) {
		String nomCuenta = null;
		
		try {nomCuenta = _cuentas.get(iban).getNombre();} catch(Exception e) {}
		
		return nomCuenta != null ? nomCuenta : iban;
	}
}