package modelo;

import java.util.Random;
import java.math.BigInteger;

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
	
	//Constructores
	public Cuenta(float dinero, String nombre, Cliente titular) {
		Random rand = new Random();
		_numCuenta = new BigInteger(20 * 5, rand);
		_iban = _ss + _numCuenta;
		_dinero = dinero;
		_titularID = titular.getID();
		_nombre = nombre;
	}
	
	public Cuenta(JSONObject cuenta) {
		try {
			_ss = cuenta.getString("ss");
			_numCuenta = cuenta.getBigInteger("numCuenta");
			_iban = _ss + _numCuenta;
			_dinero = cuenta.getFloat("dinero");
			_titularID = cuenta.getString("titular");
			_nombre = cuenta.getString("nombre");
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
		
		return o;
	}
	
	//Setters
	public void modificarDinero(float movimiento) {
		_dinero += movimiento;
	}
	
	//Verificadores
	
	//Actualizadores
	
	
	//Utiles
	//toString
	
	//toJSON
	
}
