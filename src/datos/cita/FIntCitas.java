package datos.cita;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import modelo.Cita;
import modelo.Cliente;

public interface FIntCitas {
	public boolean create(String num_cita, JSONObject cita);
	public List<JSONObject> read(List<String> clientes);
	public boolean update(String num_cita, Date nuevaFecha, Cliente cliente);
	public boolean		delete(String num_cita);
	public Cita	search(String num_cita)throws Exception;
}