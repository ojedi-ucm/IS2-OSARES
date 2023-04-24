package logica.cuenta;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Cuenta;

public class FCuentasImpl implements FCuentas {

	private SACuentas sa;
	
	public FCuentasImpl() { 
		sa = new SACuentasImpl(); 
	}

	@Override
	public boolean create(Cuenta cuenta) {
		return sa.create(cuenta);
	}
	@Override
	public List<Cuenta> read(Cliente titular) throws Exception {
		return sa.read(titular);
	}
	@Override
	public boolean update(Cuenta emisor, Cuenta receptor, float cantidad) throws Exception{
		return sa.update(emisor, receptor, cantidad);
	}
	
	@Override
	public boolean update(Cuenta emisor, String ibanReceptor, float cantidad) throws Exception{
		return sa.update(emisor, ibanReceptor, cantidad);
	}
	
	@Override
	public boolean update(Cuenta cuenta) {
		return sa.update(cuenta);
	}
	
	@Override
	public boolean update(Cuenta cuenta, float cantidad) throws Exception{
		return sa.update(cuenta, cantidad);
	}
	
	@Override
	public boolean delete(Cuenta cuenta) {
		return sa.delete(cuenta);
	}
}