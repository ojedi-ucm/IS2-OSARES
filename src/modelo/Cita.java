package modelo;

import java.util.Date;

import org.json.JSONObject;

public class Cita {
//Constantes
	private static int num_citas = 0;
//Atributos
	private Date fecha;
	private Cliente cliente;
	private String num_cita;
//Constructores
	public Cita(Date fecha, Cliente cliente) {
		this.fecha = fecha;
		this.cliente = cliente;
		this.num_cita = Integer.toString(num_citas);
		num_citas++;
	}
//Getters
	
	public Date getFecha() {
		return fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public String getnum_cita() {
		return num_cita;
	}
	
//Setters
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
//Verificadores
	
//Actualizadores

//Utiles
	//toString

	//toJSON
	public JSONObject toJSONObject() {
		JSONObject j = new JSONObject();
		JSONObject o = new JSONObject();
		
		o.put("fecha", fecha);
		o.put("cliente", cliente);
		j.put(num_cita, o);
		
		return j;
	}
}