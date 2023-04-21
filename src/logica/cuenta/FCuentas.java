package logica.cuenta;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Cuenta;

public interface FCuentas {
	public boolean create(Cliente cuenta);
	public List<Cuenta> read(Cliente titular);
	public boolean update(Cuenta emisor, Cuenta receptor, float cantidad);
	public boolean delete(String iban);
	public Cuenta consultar(String iban);	
}