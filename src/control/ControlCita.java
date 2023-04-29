package control;

import java.util.Date;

import logica.cita.FCitas;
import logica.cita.FCitasImpl;
import modelo.Cita;

public class ControlCita {
	
	private FCitas fCita;
	
	ControlCita(){
		this.fCita = new FCitasImpl();
	}
	
	public void createCita(Cita cita) {
		fCita.create(cita);
	}
	
	public void deleteCita(Cita cita) {
		fCita.delete(cita);
	}
	
	public void searchCita(String num_cita)throws Exception {
		fCita.consultar(num_cita);
	}
	
	public void updateCita(Cita cita, Date nuevaFecha) {
		fCita.update(cita, nuevaFecha);
	}
	
}