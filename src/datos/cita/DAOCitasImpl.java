package datos.cita;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;

import modelo.Cita;
import modelo.Cliente;

public class DAOCitasImpl implements DAOCitas {
//Constantes
	private final String ID_DAO = "citas";
	private final String PATH = "./resources/bd.json";
//Atributos
	private JSONObject _bd;
	private JSONObject _bdCitas;
//Constructor
	public DAOCitasImpl() throws Exception{
		InputStream in = new FileInputStream(PATH);
		loadCitas(in);
	}
	
//metodos privados a�adidos
	private void loadCitas(InputStream in) throws Exception {
		try {
			_bd = new JSONObject(new JSONTokener(in));
			_bdCitas = _bd.getJSONObject(ID_DAO);
		} catch(Exception e ) {
			throw new Exception(e);
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
		_bdCitas.put(IdCita, cita);	
		return saveChanges();
	}
	
	@Override
	public List<JSONObject> read(List<String> IdCitasCliente) {
		List<JSONObject> res = new ArrayList<>();
		
		for(String c: IdCitasCliente) {
			JSONObject o = _bdCitas.getJSONObject(c);
			res.add(o);
		}
		
		return res;
	}
	
	@Override
	public boolean update(String IdCita,JSONObject cita) {
		_bdCitas.put(IdCita, cita);

		return saveChanges();
	}
	
	@Override
	public boolean delete(String IdCita) {
		_bdCitas.remove(IdCita);
		
		return saveChanges();
	}
	
	@Override
	public Cita search(String IdCita) throws Exception{
		try {
			return new Cita(_bdCitas.getJSONObject(IdCita));
		}
		catch(Exception e) {
			throw new Exception("La cita n�mero " + IdCita + " no existe.");
		}
	}
}