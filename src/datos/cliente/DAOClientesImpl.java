package datos.cliente;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;

import modelo.Cliente;

public class DAOClientesImpl implements DAOClientes {
//Constantes
	private final String ID_DAO = "clientes";
	private final String PATH = "./resources/bd.json";
//Atributos
	JSONObject bd;
	JSONObject datosClientes;
//Constructor
	public DAOClientesImpl() throws Exception {
		loadClientes();
	}

//Metodos privados

	private void loadClientes() {
		try {
			InputStream in = new FileInputStream(PATH);
			bd = new JSONObject(new JSONTokener(in));
			datosClientes = bd.getJSONObject(ID_DAO);
		} catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	private boolean saveChanges() {
		try (FileWriter file = new FileWriter(PATH)) {
			bd.put(ID_DAO, datosClientes);
		    file.write(bd.toString());
		    file.flush();
		    return true;
		} catch (IOException e) {
		    e.printStackTrace();
		    return false;
		}
	}
	
//CRUDS
	@Override
	public boolean create(String IdCliente, JSONObject cliente) {
		loadClientes();
		
		if(datosClientes.has(IdCliente))
			return false;
		else {
			datosClientes.put(IdCliente, cliente);
			saveChanges();
			return true;
		}
		
	}
	
	@Override
	public List<Cliente> read() {
		return null;
	}
	
	@Override
	public boolean update(String IdCliente, JSONObject usuario) {
		loadClientes();
		
		if(datosClientes.has(IdCliente)) {
			datosClientes.put(IdCliente, usuario);
			saveChanges();
			return true;
		}
		else
			return false;
	}
	
	@Override
	public boolean delete(String borrado) {
		loadClientes();
		
		if(!datosClientes.has(borrado))
			return false;
		else {
			datosClientes.remove(borrado);
			saveChanges();
			return true;
		}
	}
	
	@Override
	public JSONObject search(String id) {
		loadClientes();

		if(datosClientes.has(id)) 
			return datosClientes.getJSONObject(id);
		else
			return null;
			
	}
}