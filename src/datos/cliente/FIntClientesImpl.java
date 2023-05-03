package datos.cliente;

import java.util.List;

import org.json.JSONObject;

import modelo.Cliente;

public class FIntClientesImpl implements FIntClientes {
//Constantes
	
//Atributos
	private DAOClientes dao;
//Constructor
	public FIntClientesImpl() throws Exception { dao = new DAOClientesImpl(); }
//CRUDS
	@Override
	public boolean create(Cliente cliente) {
		return dao.create(cliente);
	}
	@Override
	public List<Cliente> read() {
		return dao.read();
	}
	@Override
	public boolean update(Cliente usuario) {
		return dao.update(usuario);
	}
	@Override
	public boolean delete(String borrado) {
		return dao.delete(borrado);
	}
	@Override
	public JSONObject search(String id) {
		return dao.search(id);
	}		
}