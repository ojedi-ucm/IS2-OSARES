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
		} catch(Exception e) { System.out.println(e.toString() + "in model Cita"); }
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
	public void setFecha(Date fecha) {
		_fecha = fecha;
	}
	
	public void setMotivo(String motivo) {
		_motivo = motivo;
	}


	//Utiles

	public String toString() {
		return toJSONObject().toString();
	}
	
	public JSONObject toJSONObject() {
		JSONObject res = new JSONObject();
		
		res.put("fecha", _fecha.getTime());
		res.put("titular", _titularID);
		res.put("motivo", _motivo);
		
		return res;
	}
}