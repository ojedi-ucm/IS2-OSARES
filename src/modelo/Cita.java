package modelo;
 
import java.util.Date;

import org.json.JSONObject;

public class Cita {

	private String _idCita;
	private Date _fecha;
	private String _titularID;
	private String _motivo;
	
	//Constructores
	
	public Cita(Date fecha, String titularID, String motivo) {
		_fecha = fecha;
		_titularID = titularID;
		_idCita = _titularID + _fecha.getTime();
		_motivo = motivo;
	}
	
	public Cita(JSONObject cita) {
		try {
			_fecha = new Date(cita.getLong("fecha"));
			_titularID = cita.getString("titular");
			_idCita = _titularID + _fecha.getTime();
			_motivo = cita.getString("motivo");
		} catch(Exception e) { e.printStackTrace(); }
	}
			
	//Getters
	
	public Date getFecha() {
		return _fecha;
	}

	public String getTitularID() {
		return _titularID;
	}
	
	public String getIdCita() {
		return _idCita;
	}
	
	public String getMotivo() {
		return _motivo;
	}
	
	//Setters
	
	//Verificadores
	
	//Actualizadores

	//Utiles

	public String toString() {
		return toJSONObject().toString();
	}
	
	public JSONObject toJSONObject() {
		JSONObject res = new JSONObject();
		JSONObject o = new JSONObject();
		
		o.put("fecha", _fecha.getTime());
		o.put("titular", _titularID);
		o.put("motivo", _motivo);
		res.put(_idCita, o);
		
		return res;
	}
}