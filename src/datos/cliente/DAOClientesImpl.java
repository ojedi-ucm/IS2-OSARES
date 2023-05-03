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
		InputStream in = new FileInputStream(PATH);
		loadClientes(in);
	}

//Metodos privados

	private void loadClientes(InputStream in) throws Exception {
		try {
			bd = new JSONObject(new JSONTokener(in));
			datosClientes = bd.getJSONObject(ID_DAO);
		} catch(Exception e ) {
			throw new Exception(e);
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
	public boolean create(Cliente cliente) {
		if(datosClientes.has(cliente.getId()))
			return false;
		else {
			datosClientes.put(cliente.getId(), cliente.toJSONObect());
			saveChanges();
			return true;
		}
		
	}
	
	@Override
	public List<Cliente> read() {
		return null;
	}
	
	@Override
	public boolean update(Cliente usuario) {
		if(datosClientes.has(usuario.getId())) {
			datosClientes.put(usuario.getId(), usuario.toJSONObect());
			saveChanges();
			return true;
		}
		else
			return false;
	}
	
	@Override
	public boolean delete(String borrado) {
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

		if(datosClientes.has(id)) 
			return datosClientes.getJSONObject(id);
		else
			return null;
			
	}
}