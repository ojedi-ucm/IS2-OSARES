package logica.cuenta;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Cuenta;

public interface SACuentas {
	boolean	create(Cliente titular);
	List<Cuenta> read(Cliente titular) throws Exception;
	boolean	update(Cuenta emisor, Cuenta receptor,  float cantidad);
	boolean	delete(String iban);
	Cuenta consultar(String iban) throws Exception;
}