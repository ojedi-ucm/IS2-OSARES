package modelo;

import java.util.List;
import java.util.ArrayList;

public class Cliente {
//Constantes

//Atributos
	private List<String> _ibans;

//Constructores
	public Cliente() {
		_ibans = new ArrayList<>();
		_ibans.add("ES211037195156466404157513655163524");
	}
//Getters
	
	public List<String> getCuentas() {
		return _ibans;
	}
	
	public String getID() {
		return "cliente1@gmail.com";
	}
	
	public void addCuenta(String iban) {
		_ibans.add(iban);
	}
	
//Setters
	
//Verificadores
	
//Actualizadores

//Utiles
	//toString

	//toJSON
}