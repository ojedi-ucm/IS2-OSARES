package datos.cuenta;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Cuenta;

public interface DAOCuentas {
	public boolean		create(ArrayList<Cliente> titulares);
	public List<Cuenta>	read(ArrayList<Cliente> titulares);
	public boolean		update(Cliente emisor,
							   Cliente receptor,
							   float cantidad);
	public Cuenta		search(long IBAN);
}