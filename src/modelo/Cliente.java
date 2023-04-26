package modelo;

import java.util.List;

import org.json.JSONObject;

import netscape.javascript.JSObject;

public class Cliente {
//Constantes

//Atributos
	private static int idCont = 0;
	private String id;
	private String password;
	private String name;
	private int tlf;
	private List<String> listaCuentas;
	private List<String> listaCitas;
	//temporal
	private String rol;

//Constructores
	public Cliente(String ID, String contrasena, String nombre, int telefono, String rol) {
		id = ID;
		password = contrasena;
		name = nombre;
		tlf = telefono;
		this.rol = rol;
	}
	
	public Cliente(JSONObject informacion) {
		
		id = informacion.getString("id");
		password = informacion.getString("password");
		name = informacion.getString("name");
		tlf = informacion.getInt("tlf");
		listaCuentas = (List<String>) informacion.get("listaCuentas");
		listaCitas = (List<String>) informacion.get("listaCitas");
		rol = informacion.getString("rol");
	}
//Getters
	public int getIdCont() {
		return idCont;
	}
	
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
	
	public String getRol() {
		return rol;
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
		info.put("listaCuentas", listaCuentas);
		info.put("listaCitas", listaCitas);
		info.put("rol", rol);
		
		return info;
	}
}