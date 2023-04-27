package control;

import logica.cliente.FClientes;
import modelo.Cliente;

public class ControlCliente {
	
	private FClientes _fachada;

	public ControlCliente() {
		
	}
	
	public void crearCliente(Cliente cliente) {
		_fachada.create(cliente);
	}
	
	public void eliminarCliente(Cliente cliente) {
		_fachada.delete(cliente);
	}
	
	public void buscarCliente(String id) {
		_fachada.search(id);
	}
	
	public void actualizarCliete(Cliente cliente) {
		_fachada.update(cliente);
	}
	
	public void iniSesion(String id, String contrasena) {
		_fachada.online(id, contrasena);
	}
}