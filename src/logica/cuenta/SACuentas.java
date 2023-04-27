package logica.cuenta;

import java.util.List;

import modelo.Cliente;
import modelo.Cuenta;

public interface SACuentas {
	boolean	create(Cuenta cuenta);
	List<Cuenta> read(Cliente titular) throws Exception;
	boolean	update(Cuenta emisor, Cuenta receptor, float cantidad) throws Exception;
	boolean update(Cuenta emisor, String ibanReceptor, float cantidad) throws Exception;
	boolean update(Cuenta cuenta);
	boolean update(Cuenta cuenta, float cantidad) throws Exception;
	boolean	delete(Cuenta cuenta);
}