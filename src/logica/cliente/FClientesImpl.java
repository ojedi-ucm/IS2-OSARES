package logica.cliente;

import java.util.List;

import modelo.Cliente;

public class FClientesImpl implements FClientes {
//Constantes
	
//Atributos
	private SAClientes sa;
//Constructor
	public FClientesImpl() { sa = new SAClientesImpl(); }
//CRUDOS
	@Override
	public boolean create(Cliente cliente) {
		return sa.create(cliente);
	}

	@Override
	public List<Cliente> read() {
		return sa.read();
	}

	@Override
	public boolean update(Cliente usuario) {
		return sa.update(usuario);
	}

	@Override
	public boolean delete(Cliente borrado) {
		return sa.delete(borrado);
	}

	@Override
	public Cliente online(int id, String password) {
		return sa.online(id, password);
	}

	@Override
	public Cliente search(int id) {
		return sa.search(id);
	}

}