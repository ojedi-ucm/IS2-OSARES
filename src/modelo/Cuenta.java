package modelo;

import java.util.List;

import org.json.JSONObject;

public class Cuenta {
	//Constantes
	
	//Atributos
	private String _ss = "ES";
	private long _iban;
	private float _dinero;
	private List<Cliente> _titulares;
	
	//Constructores
	public Cuenta(float dineroIni, List<Cliente> titulares) {
		//_iban = iban; falta
		_dinero = dineroIni;
		_titulares = titulares;
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
	
	public List<Cliente> getTitulares() {
		return _titulares;
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
	public boolean addTitular(Cliente nuevoTitular) {
		return _titulares.add(nuevoTitular);
	}
	
	public boolean removeTitular(Cliente titular) {
		return _titulares.remove(titular);
	}
	
	
	//Utiles
	//toString
	
	//toJSON
	
}
