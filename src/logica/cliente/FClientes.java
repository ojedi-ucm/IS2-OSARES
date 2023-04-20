package logica.cliente;

import java.util.List;
import modelo.Cliente;

public interface FClientes {
	public	boolean			create(Cliente cliente);
	public	List<Cliente>	read();
	public	boolean			update(Cliente usuario);
	public	boolean			delete(Cliente borrado);
	public	Cliente			online(int id, String password);
	public 	Cliente			search(int id);
}