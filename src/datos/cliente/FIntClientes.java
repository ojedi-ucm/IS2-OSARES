package datos.cliente;

import java.util.List;

import org.json.JSONObject;

import modelo.Cliente;

public interface FIntClientes {
	public boolean			create(String IdCliente, JSONObject cliente);
	public List<Cliente>	read();
	public boolean			update(String IdCliente,JSONObject usuario);
	public boolean			delete(String borrado);
	public JSONObject		search(String id);
}