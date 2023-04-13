package datos.cuenta;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Cuenta;

public class FIntCuentasImpl implements FIntCuentas {
//Constantes
	
//Atributos
	private DAOCuentas dao;
//Constructor
	public FIntCuentasImpl() { dao = new DAOCuentasImpl(); }
//CRUDS
	@Override
	public boolean create(ArrayList<Cliente> titulares) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Cuenta> read(ArrayList<Cliente> titulares) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean update(Cliente emisor, Cliente receptor, float cantidad) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Cuenta search(long IBAN) {
		// TODO Auto-generated method stub
		return null;
	}
}