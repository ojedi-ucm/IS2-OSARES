package modelo;

import java.util.Date;

public class Cita {
//Constantes

//Atributos
	Date fecha;
	Cliente cliente;
//Constructores
	public Cita(Date fecha, Cliente cliente) {
		this.fecha = fecha;
		this.cliente = cliente;
	}
//Getters
	
	public Date getFecha() {
		return fecha;
	}

	public Cliente getCliente() {
		return cliente;
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
}