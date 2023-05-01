package datos.cita;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import modelo.Cita;
import modelo.Cliente;

public interface FIntCitas {
	public boolean create(String IdCita, JSONObject cita);
	public List<JSONObject> read(List<String> IdCitasCliente);
	public boolean update(String IdCita, JSONObject cita);
	public boolean		delete(String IdCita);
	public Cita	search(String IdCita)throws Exception;
}