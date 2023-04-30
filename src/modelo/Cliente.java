package modelo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Cliente {
//Constantes

//Atributos
	//private static int idCont = 0;
	private String id;
	private String password;
	private String name;
	private int tlf;
	private List<String> listaCuentas = new ArrayList<String>();
	private List<String> listaCitas = new ArrayList<String>();

//Constructores
	public Cliente(String ID, String contrasena, String nombre, int telefono) {
		try {
			id = ID;
			password = contrasena;
			name = nombre;
			tlf = telefono;
		}catch(Exception e) {
			
		}
		
	}
	
	public Cliente(JSONObject informacion) {
		try {
			id = informacion.getString("id");
			password = informacion.getString("password");
			name = informacion.getString("name");
			tlf = informacion.getInt("tlf");
			JSONArray auxCuentas = informacion.getJSONArray("listaCuentas");
			for (int i = 0; i < auxCuentas.length(); i++) {
				String IBAN = auxCuentas.getString(i);
				listaCuentas.add(IBAN);
			}
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
	/*public int getIdCont() {
		return idCont;
	}*/
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public int getTlf() {
		return tlf;
	}
	
	public List<String> getCitas(){
		return listaCitas;
	}
	
	public List<String> getCuentas(){
		return listaCuentas;
	}
	
	public String getPassword() {
		return password;
	}
	
//Setters
	/*public void setIdCont(int num) {
		idCont = num;
	}*/
	
	public void setName(String nombre) {
		name = nombre;
	}
	
	/*public void setDni(String dni) {
		this.dni = dni;
	}*/
	
	public void setPassword(String contrasena) {
		password = contrasena;
	}
	
	public void setPhone(int telefono) {
		tlf = telefono;
	}
//Verificadores
	
//Actualizadores
	/*public void inc() {
		//TODO
	}
	
	public void dcr() {
		//TODO
	}*/
	
	public void addCita(String cita) {
		if(!listaCitas.contains(cita))
			listaCitas.add(cita);
	}
	
	public void addCuenta(String cuenta) {
		if(!listaCuentas.contains(cuenta))
			listaCuentas.add(cuenta);
	}
	
	public void removeCita(String cita) {
		if(listaCitas.contains(cita))
			listaCitas.remove(cita);
	}
	
	public void removeCuenta(String cuenta) {
		if(listaCuentas.contains(cuenta))
			listaCuentas.remove(cuenta);
	}
	
//Utiles
	//toString
	public String toString() {
		return toJSONObect().toString();
	}
	//toJSON
	public JSONObject toJSONObect() {
		
		JSONObject info = new JSONObject();
		info.put("id", id);
		//info.put("dni", dni);
		info.put("password", password);
		info.put("name", name);
		info.put("tlf", tlf);
		JSONArray Array_Cuentas = new JSONArray();
		for(String bi : listaCuentas) {
			Array_Cuentas.put(bi);
		}
		info.put("listaCuentas", Array_Cuentas);
		JSONArray Array_Citas = new JSONArray();
		for(String bi : listaCitas) {
			Array_Citas.put(bi);
		}
		info.put("listaCitas", Array_Citas);
		//info.put("rol", rol);
		
		return info;
	}
}