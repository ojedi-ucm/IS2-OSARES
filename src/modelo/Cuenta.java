package modelo;

import org.json.JSONObject;

public class Cuenta {
	//Constantes
	
	//Atributos
	private String _ss = "ES";
	private long _iban;
	private float _dinero;
	private Cliente _titular;
	
	//Constructores
	public Cuenta(long iban, float dineroIni, Cliente titular) {
		_iban = iban;
		_dinero = dineroIni;
		_titular = titular;
	}
	
	public Cuenta(JSONObject cuenta) {
		
	}
	
	//Getters
	public long getIBAN() {
		return _iban;
	}
	
	public float getDinero() {
		return _dinero;
	}
	
	public Cliente getTitular() {
		return _titular;
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
	public void changeTitular(Cliente nuevoTitular) {
		_titular = nuevoTitular;
	}
	
	
	//Utiles
	//toString
	
	//toJSON
	
}
