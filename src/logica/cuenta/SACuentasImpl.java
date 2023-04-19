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
	public boolean create(ArrayList<Cuenta> titulares) {
		// TODO Auto-generated method stub
		//return dao.create(titulares);
		return false;
	}
	@Override
	public List<Cuenta> read() {
		// TODO Auto-generated method stub
		return null;
	}	
	@Override
	public List<Cuenta> readCuentasCliente(Cliente titular) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean update(Cuenta emisor, Cuenta receptor, float cantidad) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(long IBAN, Cuenta receptor) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Cuenta consultar(long IBAN) {
		// TODO Auto-generated method stub
		return null;
	}
}