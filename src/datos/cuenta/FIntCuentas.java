package datos.cuenta;

import java.util.List;

import org.json.JSONObject;

public interface FIntCuentas {
	public boolean create(String id, JSONObject cuenta);
	public List<JSONObject>	read(List<String> titularIDs);
	public boolean update(String idEmisor, JSONObject emisor, String idReceptor, JSONObject receptor);
	public boolean update(String id, JSONObject cuenta);
	public boolean delete(String id);
	public JSONObject search(String iban) throws Exception;
}