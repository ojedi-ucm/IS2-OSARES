package logica.cuenta;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Cuenta;

public interface FCuentas {
	public boolean		create(ArrayList<Cuenta> titulares);
	public List<Cuenta> read();
	public List<Cuenta> readCuentasCliente(Cliente titular);
	public boolean		update(Cuenta emisor,
							   Cuenta receptor,
							   float cantidad);
	public boolean		delete(long IBAN, Cuenta receptor);
	public Cuenta		consultar(long IBAN);	
}