package datos.cita;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;

import modelo.Cita;

public class DAOCitasImpl implements DAOCitas {
//Constantes
	private final String ID_DAO = "citas";
	private final String PATH = "./resources/bd.json";
//Atributos
	private JSONObject _bd;
	private JSONObject _bdCitas;
//Constructor
	public DAOCitasImpl() throws Exception{
		loadCitas();
	}
	
//metodos privados a�adidos
	private void loadCitas() {
		try {
			InputStream in = new FileInputStream(PATH);
			_bd = new JSONObject(new JSONTokener(in));
			_bdCitas = _bd.getJSONObject(ID_DAO);
		} catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	private boolean saveChanges() {
		try (FileWriter file = new FileWriter(PATH)) {
			_bd.put(ID_DAO, _bdCitas);
		    file.write(_bd.toString());
		    file.flush();
		    return true;
		} catch (IOException e) {
		    e.printStackTrace();
		    return false;
		}
	}
	
//CRUDS	
	@Override
	public boolean create(String IdCita, JSONObject cita) {
		loadCitas();
		
		_bdCitas.put(IdCita, cita);	
		return saveChanges();
	}
	
	@Override
	public List<JSONObject> read(List<String> IdCitasCliente) {
		loadCitas();
		
		List<JSONObject> res = new ArrayList<>();
		
		for(String c: IdCitasCliente) {
			JSONObject o = _bdCitas.getJSONObject(c);
			res.add(o);
		}
		
		return res;
	}
	
	@Override
	public boolean update(String IdCita,JSONObject cita) {
		loadCitas();
		
		_bdCitas.put(IdCita, cita);

		return saveChanges();
	}
	
	@Override
	public boolean delete(String IdCita) {
		loadCitas();
		
		_bdCitas.remove(IdCita);
		
		return saveChanges();
	}
	
	@Override
	public Cita search(String IdCita) throws Exception{
		loadCitas();
		
		try {
			return new Cita(_bdCitas.getJSONObject(IdCita));
		}
		catch(Exception e) {
			throw new Exception("La cita con ID " + IdCita + " no existe.");
		}
	}
}