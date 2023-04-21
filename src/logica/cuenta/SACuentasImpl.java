package logica.cuenta;

import java.util.ArrayList;
import java.util.List;

import datos.cuenta.FIntCuentas;
import datos.cuenta.FIntCuentasImpl;
import modelo.Cliente;
import modelo.Cuenta;

public class SACuentasImpl implements SACuentas {
//Constantes
	
//Atributos
	private FIntCuentas dao;
//Constructor
	SACuentasImpl() { dao = new FIntCuentasImpl(); }
//CRRUDS
	@Override
	public boolean create(Cliente titular) {
		return dao.create(titular);
	}
	@Override
	public List<Cuenta> read(Cliente titular) {
		return dao.read(titular);
	}
	@Override
	public boolean update(Cuenta emisor, Cuenta receptor, float cantidad) {
		return dao.update(emisor, receptor, cantidad);
	}
	@Override
	public boolean delete(String iban) {
		return dao.delete(iban);
	}
	@Override
	public Cuenta consultar(String iban) {
		return dao.search(iban);
	}
}