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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cliente> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Cliente usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cliente borrado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cliente online(String dni, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente consulta(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}