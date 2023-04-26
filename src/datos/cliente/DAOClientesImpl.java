package datos.cliente;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;

import modelo.Cliente;

public class DAOClientesImpl implements DAOClientes {
//Constantes

//Atributos
	JSONObject bd;
	JSONObject datosClientes;
//Constructor
	public DAOClientesImpl() throws Exception {
		InputStream in = new FileInputStream("./resources/bd.json");
		loadClientes(in);
	}

//Metodos privados

	private void loadClientes(InputStream in) throws Exception {
		try {
			bd = new JSONObject(new JSONTokener(in));
			datosClientes = bd.getJSONObject("clientes");
		} catch(Exception e ) {
			throw new Exception(e);
		}
	}
	
//CRUDS
	@Override
	public boolean create(Cliente cliente) {
		// TODO Auto-generated method stub
		if(datosClientes.has(cliente.getId()))
			return false;
			
		else {
			datosClientes.put(cliente.getId(), cliente);
			return true;
		}
		
	}
	
	@Override
	public List<Cliente> read() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean update(Cliente usuario) {
		// TODO Auto-generated method stub
		if(datosClientes.has(usuario.getId())) {
			datosClientes.put(usuario.getId(), usuario);
			return true;
		}
		else
			return false;
	}
	
	@Override
	public boolean delete(Cliente borrado) {
		// TODO Auto-generated method stub
		if(!datosClientes.has(borrado.getId()))
			return false;
		else {
			datosClientes.remove(borrado.getId());
			return true;
		}
	}
	
	@Override
	public Cliente search(String id) {
		// TODO Auto-generated method stub
		return new Cliente(datosClientes.getJSONObject(id));
			
	}
}