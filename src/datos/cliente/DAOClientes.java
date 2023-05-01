package datos.cliente;

import java.util.List;

import org.json.JSONObject;

import modelo.Cliente;

public interface DAOClientes {
	public boolean			create(Cliente cliente);
	public List<Cliente>	read();
	public boolean			update(Cliente usuario);
	public boolean			delete(String borrado);
	public JSONObject			search(String id);
}