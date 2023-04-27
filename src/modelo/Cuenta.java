package modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.math.BigInteger;

import java.time.LocalDate;

import org.json.JSONObject;

public class Cuenta {
	//Constantes
	
	//Atributos
	private String _ss = "ES21";
	private BigInteger _numCuenta;
	private String _iban;
	private float _dinero;
	private String _titularID;
	private String _nombre;
	private Map<String, Object> _transacciones;
	
	//Constructores
	public Cuenta(float dinero, String nombre, Cliente titular) {
		Random rand = new Random();
		_numCuenta = new BigInteger(20 * 5, rand);
		_iban = _ss + _numCuenta;
		_dinero = dinero;
		_titularID = titular.getID();
		_nombre = nombre;
		_transacciones = new HashMap<>();
	}
	
	public Cuenta(JSONObject cuenta) {
		try {
			_ss = cuenta.getString("ss");
			_numCuenta = cuenta.getBigInteger("numCuenta");
			_iban = _ss + _numCuenta;
			_dinero = cuenta.getFloat("dinero");
			_titularID = cuenta.getString("titular");
			_nombre = cuenta.getString("nombre");
			_transacciones = new HashMap<>();
			
			JSONObject transacciones = cuenta.getJSONObject("transacciones");
			
			if(transacciones != null) {
				for(String id: transacciones.keySet()) {
					Map<String, Object> datos = new HashMap<>();
					
					datos.put("desde", transacciones.getJSONObject("id").getString("desde"));
					datos.put("hasta", transacciones.getJSONObject("id").getString("hasta"));
					datos.put("cantidad", transacciones.getJSONObject("id").getFloat("cantidad"));
					
					_transacciones.put(id, datos);
				}
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//Getters
	public String getIBAN() {
		return _iban;
	}
	
	public float getDinero() {
		return _dinero;
	}
	
	public String getTitularID() {
		return _titularID;
	}
	
	public String getSS() {
		return _ss;
	}
	
	public BigInteger getNumCuenta() {
		return _numCuenta;
	}
	
	public String getNombre() {
		return _nombre;
	}
	
	public JSONObject getJSON() {
		JSONObject o = new JSONObject();
		
		o.put("ss", _ss);
		o.put("numCuenta", _numCuenta);
		o.put("dinero", _dinero);
		o.put("titular", _titularID);
		o.put("nombre", _nombre);
		o.put("transacciones", new JSONObject(_transacciones));
		
		return o;
	}
	
	public List<Map<String, Object>> getTransacciones() {
		List<Map<String, Object>> res = new ArrayList<>();
		
		for(Object o: _transacciones.values()) {
			Map<String, Object> map = (Map<String, Object>) o;
			res.add(map);
		}
		
		return res;
	}
	
	//Setters
	public void modificarDinero(float movimiento) throws Exception {
		if(_dinero + movimiento >= 0)
			_dinero += movimiento;
		else
			throw new Exception("No hay fondos suficientes en la cuenta " + _nombre);
	}
	
	public void nuevaTransaccion(String id, String desde, String hasta, float cantidad) {
		Map<String, Object> transaccion = new HashMap<>();
		
		transaccion.put("desde", desde);
		transaccion.put("hasta", hasta);
		transaccion.put("cantidad", cantidad);
		
		_transacciones.put(id, transaccion);
	}
	
	//Verificadores
	
	//Actualizadores
	
	
	//Utilesx
	
	//toString
	
	//toJSON
	
}
