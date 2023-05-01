package logica.cliente;

import java.util.List;

import org.json.JSONObject;

import datos.cliente.FIntClientes;
import datos.cliente.FIntClientesImpl;
import modelo.Cliente;

public class SAClientesImpl implements SAClientes {
//Constantes
	
//Atributos
	private FIntClientes fdao;
//Constructor
	SAClientesImpl() throws Exception { fdao = new FIntClientesImpl(); }
//CRUDOS
	@Override
	public boolean create(Cliente cliente) {
		return fdao.create(cliente);
	}

	@Override
	public List<Cliente> read() {
		return fdao.read();
	}

	@Override
	public boolean update(Cliente usuario) {
		return fdao.update(usuario);
	}

	@Override
	public boolean delete(String borrado) {
		return fdao.delete(borrado);
	}

	@Override
	public Cliente online(String id, String password) {
		JSONObject aux = fdao.search(id);
		Cliente c;
		if(aux != null) {
			c = new Cliente(aux);
			if(c.getPassword().equals(password))
				return c;
			else
				return null;
		}
		else
			return null;
	}

	@Override
	public Cliente search(String id) {
		JSONObject aux = fdao.search(id);
		Cliente c;
		if(aux != null) {
			c = new Cliente(aux);
			return c;
		}
		else
			return null;
	}

}