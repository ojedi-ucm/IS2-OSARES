package vista.observers;

import java.util.Map;

import modelo.Cita;

public interface CitasObserver {
	public void updateCitas(Map<String, Cita> citas);
}
