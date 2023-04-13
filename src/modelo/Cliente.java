package modelo;

import java.util.List;

import org.json.JSONObject;

import netscape.javascript.JSObject;

public class Cliente {
//Constantes

//Atributos
	private static int idCont = 0;
	private int id;
	private String dni;
	private String password;
	private String name;
	private int tlf;
	private List<String> listaCuentas;
	private List<String> listaCitas;
	//temporal
	private String roles;

//Constructores
	public Cliente(String DNI, String contrasena, String nombre, int telefono, String rol) {
		dni = DNI;
		password = contrasena;
		name = nombre;
		tlf = telefono;
		roles = rol;
	}
	
	public Cliente() {
		
	}
//Getters
	public int getIdCont() {
		return idCont;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDni() {
		return dni;
	}
	
	public int getId() {
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
		return roles;
	}
	
//Setters
	public void setIdCont(int num) {
		idCont = num;
	}
	
	public void setName(String nombre) {
		name = nombre;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public void setPassword(String contrasena) {
		password = contrasena;
	}
	
	public void setPhone(int telefono) {
		tlf = telefono;
	}
//Verificadores
	
//Actualizadores
	public void inc() {
		//TODO
	}
	
	public void dcr() {
		//TODO
	}
	
	public void addCita(String cita) {
		listaCitas.add(cita);
	}
	
	public void addCuenta(String cuenta) {
		listaCuentas.add(cuenta);
	}
	
	public void removeCita(String cita) {
		//TODO
	}
	
	public void removeCuenta(String cuenta) {
		//TODO
	}
	
//Utiles
	//toString
	public String toString() {
		return toJSONObect().toString();
	}
	//toJSON
	public JSONObject toJSONObect() {
		return new JSONObject();
	}
}