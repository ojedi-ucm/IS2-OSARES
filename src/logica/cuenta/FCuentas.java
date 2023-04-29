package logica.cuenta;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import modelo.Cliente;
import modelo.Cuenta;

public interface FCuentas {
	public boolean create(Cuenta cuenta);
	public List<Cuenta> read(Cliente titular) throws Exception;
	public boolean update(Cuenta emisor, Cuenta receptor, float cantidad) throws Exception;
	public boolean update(Cuenta emisor, String ibanReceptor, float cantidad) throws Exception;
	public boolean update(Cuenta cuenta);
	public boolean update(Cuenta cuenta, float cantidad) throws Exception;
	public boolean delete(Cuenta cuenta);
	public Cuenta search(String iban);
}