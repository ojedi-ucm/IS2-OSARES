package logica.cliente;

import java.util.List;
import modelo.Cliente;

public interface FClientes {
	public	boolean			create(Cliente cliente);
	public	List<Cliente>	read();
	public	boolean			update(Cliente usuario);
	public	boolean			delete(String borrado);
	public	Cliente			online(String id, String password);
	public 	Cliente			search(String id);
}