package datos.cliente;

import java.util.List;

import modelo.Cliente;

public interface FIntClientes {
	public boolean			create(Cliente cliente);
	public List<Cliente>	read();
	public boolean			update(Cliente usuario);
	public boolean			delete(Cliente borrado);
	public Cliente			search(int id);
}