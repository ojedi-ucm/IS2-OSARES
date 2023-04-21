package datos.cuenta;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Cuenta;

public interface FIntCuentas {
	public boolean create(Cliente titular);
	public List<Cuenta>	read(Cliente titular) throws Exception;
	public boolean update(Cuenta emisor, Cuenta receptor, float cantidad);
	public Cuenta search(String iban) throws Exception;
	public boolean delete(String iban);
}