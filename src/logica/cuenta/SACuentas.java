package logica.cuenta;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import modelo.Cliente;
import modelo.Cuenta;

public interface SACuentas {
	boolean	create(Cuenta cuenta);
	List<Cuenta> read(Cliente titular) throws Exception;
	boolean	update(Cuenta emisor, Cuenta receptor, float cantidad);
	boolean update(Cuenta emisor, String ibanReceptor, float cantidad);
	boolean update(Cuenta cuenta);
	boolean update(Cuenta cuenta, float cantidad);
	boolean	delete(Cuenta cuenta);
}