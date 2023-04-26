package logica.cliente;

import java.util.List;

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
	public boolean delete(Cliente borrado) {
		return fdao.delete(borrado);
	}

	@Override
	public Cliente online(String id, String password) {
		Cliente aux = fdao.search(id);
		if(aux != null)
			if(aux.getPassword().equals(password))
				return aux;
			else
				return null;
		else
			return null;
	}

	@Override
	public Cliente search(String id) {
		return fdao.search(id);
	}

}