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
	public FIntCuentasImpl() { 
		try {
			dao = new DAOCuentasImpl();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//CRUDS
	@Override
	public boolean create(Cliente titular) {
		return dao.create(titular);
	}
	@Override
	public List<Cuenta> read(Cliente titular) throws Exception {
		return dao.read(titular);
	}
	@Override
	public boolean update(Cuenta emisor, Cuenta receptor, float cantidad) {
		return dao.update(emisor, receptor, cantidad);
	}
	@Override
	public Cuenta search(String iban) throws Exception {
		return dao.search(iban);
	}

	@Override
	public boolean delete(String iban) {
		return dao.delete(iban);
	}
}