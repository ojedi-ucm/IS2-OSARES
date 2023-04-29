package modelo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Cliente {
//Constantes

//Atributos
	private List<String> listaCitas = new ArrayList<String>();

//Constructores
	public Cliente() {
		
	}
	
	public Cliente(JSONObject informacion) {
		try {
			JSONArray auxCitas = informacion.getJSONArray("listaCitas");
			for (int i = 0; i < auxCitas.length(); i++) {
				String num_cita = auxCitas.getString(i);
				listaCitas.add(num_cita);
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
//Getters
	public List<String> getCitas(){
		return listaCitas;
	}
//Setters
	
//Verificadores
	
//Actualizadores

//Utiles
	//toString

	//toJSON
public JSONObject toJSONObect() {
		
		JSONObject info = new JSONObject();
		JSONArray Array_Citas = new JSONArray();
		for(String bi : listaCitas) {
			Array_Citas.put(bi);
		}
		info.put("listaCitas", Array_Citas);
		//info.put("rol", rol);
		
		return info;
	}
}