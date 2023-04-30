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
		
		/*try {
			for(Cita c: _fCitas.readCitasCliente(_titular))
				_citas.put(c.getIdCita(), c);
		} catch(Exception e) {
			System.out.println(e.toString());
		}*/
	}
	
	// ------ Métodos Públicos ---------
	
	public void addObserver(CitasObserver obs) {
		_observers.add(obs);
	}
	
	
	// ----- CRUDS con FSACitas -------
	public void createCita(Cita cita) {
		_fCitas.create(cita);
	}
	
	public void deleteCita(Cita cita) {
		_fCitas.delete(cita);
	}
	
	public void searchCita(String num_cita)throws Exception {
		_fCitas.consultar(num_cita);
	}
	
	public void updateCita(Cita cita, Date nuevaFecha) {
		_fCitas.update(cita, nuevaFecha);
	}
	
}