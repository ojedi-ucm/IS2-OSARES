package modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.BigInteger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

public class Cuenta {
	//Constantes
	
	//Atributos
	private String _ss;
	private BigInteger _numCuenta;
	private String _iban;
	private float _dinero;
	private String _titularID;
	private String _nombre;
	private Map<String, JSONObject> _transacciones;
	
	//Constructores
	public Cuenta(String nombre, String ss, BigInteger numCuenta, Cliente titular) {
		_ss = ss;
		_numCuenta = numCuenta;
		_iban = _ss + _numCuenta;
		_dinero = 0;
		_titularID = titular.getId();
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
			
			//System.out.println(transacciones);
			
			if(transacciones != null) {
				for(String id: transacciones.keySet()) {
					JSONObject datos = new JSONObject();
					
					datos.put("desde", transacciones.getJSONObject(id).getString("desde"));
					datos.put("hasta", transacciones.getJSONObject(id).getString("hasta"));
					datos.put("cantidad", transacciones.getJSONObject(id).getFloat("cantidad"));
					datos.put("fecha", transacciones.getJSONObject(id).getString("fecha"));
					
					_transacciones.put(id, datos);
				}
			}
		} catch(Exception e) {
			System.out.println(e.toString() + "in model Cuenta");
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
	
	public List<JSONObject> getTransacciones() {
		List<JSONObject> res = new ArrayList<>();
		
		for(JSONObject transaccion: _transacciones.values())
			res.add(transaccion);
		
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
		JSONObject transaccion = new JSONObject();
		LocalDateTime hoy = LocalDateTime.now();
		
		transaccion.put("desde", desde);
		transaccion.put("hasta", hasta);
		transaccion.put("cantidad", cantidad);
		transaccion.put("fecha", hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		
		_transacciones.put(id, transaccion);
	}
	
	//Verificadores
	
	//Actualizadores
	
	
	//Utilesx
	
	//toString
	
	//toJSON
	
}
