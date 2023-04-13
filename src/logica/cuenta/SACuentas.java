package logica.cuenta;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Cuenta;

public interface SACuentas {
	boolean		create(ArrayList<Cuenta> titulares);
	List<Cuenta> read();
	List<Cuenta> readCuentasCliente(Cliente titular);
	boolean		update(Cuenta emisor,
					   Cuenta receptor,
					   float cantidad);
	boolean		delete(long IBAN, Cuenta receptor);
	Cuenta		consultar(long IBAN);
}