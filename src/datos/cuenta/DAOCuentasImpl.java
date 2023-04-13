package datos.cuenta;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Cuenta;

public class DAOCuentasImpl implements DAOCuentas {
//Constantes
	
//Atributos
	//BD
//Constructor
	DAOCuentasImpl() {
		//bd = new()
	}
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