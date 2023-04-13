package logica.cliente;

import java.util.List;
import modelo.Cliente;

public interface SAClientes {
	boolean			create(Cliente cliente);
	List<Cliente>	read();
	boolean			update(Cliente usuario);
	boolean			delete(Cliente borrado);
	Cliente			online(String dni, String password);
	Cliente			consulta(int id);
}