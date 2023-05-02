package control;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import logica.cita.FCitas;
import logica.cita.FCitasImpl;
import modelo.Cita;
import modelo.Cliente;
import vista.observers.CitasObserver;

public class ControlCita {
	
	private Cliente _titular;
	
	private Map<String, Cita> _citas;
	private List<CitasObserver> _observers;
	
	private FCitas _fCitas;
	
	public ControlCita(Cliente titular){
		_titular = titular;
		reset();
	}
	
	// ------- Métodos Privados ---------
	
	private void reset() {
		_fCitas = new FCitasImpl();
		_observers = new ArrayList<>();
		_citas = new HashMap<>();
		
		try {
			for(Cita c: _fCitas.readCitasCliente(_titular))
				_citas.put(c.getIdCita(), c);
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	private void updateObs() {
		for(CitasObserver obs: _observers)
			obs.updateCitas(_citas);
	}
	
	// ------ Métodos Públicos ---------
	
	public void addObserver(CitasObserver obs) {
		_observers.add(obs);
	}
	
	
	// ----- CRUDS con FSACitas -------
	public void createCita(Date fecha, String motivo) {
		Cita nuevaCita = new Cita(fecha, _titular.getID(), motivo);
		
		_citas.put(nuevaCita.getIdCita(), nuevaCita);
		
		_fCitas.create(nuevaCita);
		
		updateObs();
	}
	
	public void searchCita(String idCita) throws Exception {
		_fCitas.search(idCita);
	}
	
	public void updateCita(Cita cita, Date fecha, String motivo) {
		cita.setFecha(fecha);
		cita.setMotivo(motivo);
		
		_citas.put(cita.getIdCita(), cita);
		
		_fCitas.update(cita);
		
		updateObs();
	}
	
	public void deleteCita(Cita cita) {
		_citas.remove(cita.getIdCita());
		
		_fCitas.delete(cita);
		
		updateObs();
	}
}