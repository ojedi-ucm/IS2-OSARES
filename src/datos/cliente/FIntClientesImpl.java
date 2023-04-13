package datos.cliente;

import java.util.List;

import modelo.Cliente;

public class FIntClientesImpl implements FIntClientes {
//Constantes
	
//Atributos
	private DAOClientes dao;
//Constructor
	public FIntClientesImpl() { dao = new DAOClientesImpl(); }
//CRUDS
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
	public Cliente search(int id) {
		// TODO Auto-generated method stub
		return null;
	}		
}