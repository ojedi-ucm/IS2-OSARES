package modelo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Cliente {
//Constantes

//Atributos
	private List<String> _listaCitas;
	private String _id;

//Constructores
	public Cliente() {
		_id = "123213213";
		_listaCitas = new ArrayList<String>();
		
		//_listaCitas.add("");
	}
	
//Getters
	public List<String> getCitas(){
		return _listaCitas;
	}
	
	public String getID() {
		return _id;
	}
//Setters
	
//Verificadores
	
//Actualizadores

//Utiles
	//toString

	//toJSON
}