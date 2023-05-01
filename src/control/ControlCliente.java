package control;

import java.util.List;

import logica.cliente.FClientesImpl;
import logica.cliente.FClientes;
import modelo.Cliente;

public class ControlCliente {
	
	public Cliente _cliente;
	
	private FClientes _fachada;

	public ControlCliente() throws Exception {
		_fachada = new FClientesImpl();
	}
	
	public boolean crearCliente(Cliente cliente) {
		if(_fachada.create(cliente)) {
			_cliente = cliente;
			return true;
		}else
			return false;
	}
	
	public void eliminarCliente(String cliente) {
		_fachada.delete(cliente);
	}
	
	public void buscarCliente(String id) {
		_fachada.search(id);
	}
	
	public void actualizarCliete(Cliente cliente) {
		_fachada.update(cliente);
	}
	
	public Cliente iniSesion(String id, String contrasena) {
		_cliente = _fachada.online(id, contrasena);
		return _cliente;
	}
}