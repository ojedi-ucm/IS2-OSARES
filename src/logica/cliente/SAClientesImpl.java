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
	SAClientesImpl() { fdao = new FIntClientesImpl(); }
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