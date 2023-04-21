package logica.cuenta;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Cuenta;

public class FCuentasImpl implements FCuentas {
//Constantes
	
//Atributos
	private SACuentas sa;
//Constructor
	public FCuentasImpl() { sa = new SACuentasImpl(); }
//CRRUDS
	@Override
	public boolean create(Cliente titular) {
		return sa.create(titular);
	}
	@Override
	public List<Cuenta> read(Cliente titular) throws Exception {
		return sa.read(titular);
	}
	@Override
	public boolean update(Cuenta emisor, Cuenta receptor, float cantidad) {
		return sa.update(emisor, receptor, cantidad);
	}
	@Override
	public boolean delete(String iban) {
		return sa.delete(iban);
	}
	@Override
	public Cuenta consultar(String iban) throws Exception {
		return sa.consultar(iban);
	}
}