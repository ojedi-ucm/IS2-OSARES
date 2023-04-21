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
	
	//Constructores
	public Cuenta(float dinero, Cliente titular) {
		Random rand = new Random();
		_numCuenta = new BigInteger(20 * 5, rand);
		_iban = _ss + _numCuenta;
		_dinero = dinero;
		_titularID = titular.getID();
	}
	
	public Cuenta(String iban, JSONObject cuenta) {
		_ss = cuenta.getString("ss");
		_numCuenta = cuenta.getBigInteger("numCuenta");
		_iban = iban;
		_dinero = cuenta.getFloat("dinero");
		_titularID = cuenta.getString("titular");
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
	//Setters
	public void sumDinero(float ingreso) {
		_dinero += ingreso;
	}
	
	public void restDinero(float gasto) {
		_dinero += gasto;
	}
	
	//Verificadores
	
	//Actualizadores
	
	
	//Utiles
	//toString
	
	//toJSON
	
}
